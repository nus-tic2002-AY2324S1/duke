package parser;

import common.Messages;
import enumerations.Keyword;
import tasks.Task;

import java.util.EnumSet;
import java.util.ArrayList;

public class Parser {
    protected ArrayList<String> parsedCommand = new ArrayList<>();
    protected final String LIST_REGEX = "^list$";
    protected final String TODO_REGEX = "^todo(?!\\s*$).+$";
    protected final String EVENT_REGEX = "^event .+ /from .+ /to .+$";
    protected final String FROM_REGEX = " /from ";
    protected final String TO_REGEX = " /to ";
    protected final String DEADLINE_REGEX = "^deadline .+ /due .+$";
    protected final String DUE_REGEX = " /due ";
    protected final String SELECT_INDEX_REGEX = "^(select|unselect) [1-9][0-9]*$";
    protected final String REMOVE_INDEX_REGEX = "^(remove) [0-9]+$";
    protected final String BYE_REGEX = "^bye$";

    public ArrayList<String> parseLoadTask(String task) {
        ArrayList<String> item = new ArrayList<>();
        String[] words = task.toLowerCase().split("\\|");

        String type = words[0].replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());
        item.add(type);

        boolean isSeleted = words[1].contains("✓");
        item.add(String.valueOf(isSeleted));

        String description = words[2];
        item.add(description);

        switch (commandType) {
            case EVENT:
                String fromDateTime = words[3];
                String toDateTime = words[4];
                item.add(fromDateTime);
                item.add(toDateTime);
                break;
            case DEADLINE:
                String dueDateTIme = words[3];
                item.add(dueDateTIme);
                break;
        }

        return item;
    }

    public String parseSaveTask(Task task) {
        String item = "";

        String type = task.getType().replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());

        String status = task.getStatus();
        String description = task.getDescription();

        switch (commandType) {
            case TODO:
                item = type + "|" + status + "|" + description;
                break;
            case EVENT:
                String fromDateTime = task.getFromDateTime();
                String toDateTime = task.getToDateTime();
                item = type + "|" + status + "|" + description + "|" + fromDateTime + "|" + toDateTime;
                break;
            case DEADLINE:
                String dueDateTIme = task.getDueDateTime();
                item = type + "|" + status + "|" + description + "|" + dueDateTIme;
                break;
        }

        return item;
    }

    public ArrayList<String> parseCommand(String command) {
        String[] words = command.trim().split(" ");
        String commandName = words[0].toUpperCase();

        if (isValidCommand(commandName)) {
            Keyword commandType = Keyword.valueOf(commandName);

            switch (commandType) {
                case TODO:
                    parseTodo(commandName, command);
                    break;
                case EVENT:
                    parseEvent(commandName, command);
                    break;
                case DEADLINE:
                    parseDeadline(commandName, command);
                    break;
                case SELECT:
                    // Fallthrough
                case UNSELECT:
                    // Fallthrough
                case REMOVE:
                    parseUpdate(commandName, command, words);
                    break;
                default:
                    parseDefault(commandName, command);
                    break;
            }
        } else {
            System.out.println(Messages.INVALID_COMMAND);
        }

        return parsedCommand;
    }

    public boolean isValidCommand(String commandName) {
        EnumSet<Keyword> commandList = EnumSet.allOf(Keyword.class);

        for (Keyword commandType : commandList) {
            boolean isMatch = commandType.toString().equalsIgnoreCase(commandName);

            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    public void parseTodo(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int size = command.length();
            setDescription(command, 5, size);
        } else {
            System.out.println(Messages.INVALID_COMMAND_FORMAT);
        }
    }

    public void parseEvent(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int index = command.indexOf(FROM_REGEX);
            setDescription(command, 6, index);
            setDateTime(command, index);
        } else {
            System.out.println(Messages.INVALID_COMMAND_FORMAT);
        }
    }

    public void parseDeadline(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int index = command.indexOf(DUE_REGEX);
            setDescription(command, 9, index);
            setDateTime(command, index);
        } else {
            System.out.println(Messages.INVALID_COMMAND_FORMAT);
        }
    }

    public void setDescription(String command, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            String description = command.substring(startIndex, endIndex);
            parsedCommand.add(description);
        } else {
            System.out.println(Messages.INVALID_DESC);
        }
    }

    public void setDateTime(String command, int index) {
        int size = command.length();
        String dateTime = command.substring(index, size);

        if (isValidDate(dateTime)) {
            splitDateTime(dateTime);
        } else {
            System.out.println(Messages.INVALID_DATE);
        }
    }

    public boolean isValidDate(String date) {
        boolean isEventDate = date.contains(FROM_REGEX) && date.contains(TO_REGEX);
        boolean isDeadlineDate = date.contains(DUE_REGEX);

        return isEventDate || isDeadlineDate;
    }

    public void splitDateTime(String date) {
        int size = date.length();

        if (date.contains(FROM_REGEX)) {
            int index = date.indexOf(TO_REGEX);

            String from = date.substring(0, 6).replace('/', '(');
            String fromTime = date.substring(6, index);
            String fromDate = from + ":" + fromTime;

            String to = date.substring(index + 1, index + 4).replace('/', ' ');
            String toTime = date.substring(index + 4, size);
            String toDate = to + ":" + toTime + ")";

            parsedCommand.add(fromDate);
            parsedCommand.add(toDate);
        } else {
            String dueDate = date.substring(0, 5).replace('/', '(');
            String dueDateTime = date.substring(5);
            String due = dueDate + ":" + dueDateTime + ")";

            parsedCommand.add(due);
        }
    }

    public void parseUpdate(String commandName, String command, String[] words) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            setIndex(words[1]);
        } else {
            System.out.println(Messages.INVALID_COMMAND_FORMAT);
        }
    }

    public void setIndex(String index) {
        int taskIndex = Integer.parseInt(index);

        if (isValidIndex(taskIndex)) {
            parsedCommand.add(index);
        } else {
            parsedCommand.add("-1");
            System.out.println(Messages.INVALID_INDEX);
        }
    }

    public boolean isValidIndex(int taskIndex) {
        return taskIndex <= Task.getListSize();
    }

    public void parseDefault(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
        } else {
            System.out.println(Messages.INVALID_COMMAND_FORMAT);
        }
    }

    public boolean isValidCommandFormat(String command) {
        return command.matches(TODO_REGEX) || command.matches(EVENT_REGEX) || command.matches(DEADLINE_REGEX) ||
                command.matches(SELECT_INDEX_REGEX) || command.matches(LIST_REGEX) ||
                command.matches(REMOVE_INDEX_REGEX) || command.matches(BYE_REGEX);
    }
}
