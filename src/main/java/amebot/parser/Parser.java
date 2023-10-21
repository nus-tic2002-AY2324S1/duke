package amebot.parser;

import amebot.common.Messages;
import amebot.common.Regex;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.util.EnumSet;
import java.util.ArrayList;

public class Parser {
    protected ArrayList<String> parsedCommand = new ArrayList<>();

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
            System.out.println(Messages.INVALID_DESC);
        }
    }

    public void parseEvent(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int index = command.indexOf(Regex.FROM_PATTERN);
            setDescription(command, 6, index);
            ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, index);
            parsedCommand.addAll(parsedDateTime);
        } else {
            System.out.println(Messages.INVALID_DESC_DATE);
        }
    }

    public void parseDeadline(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int index = command.indexOf(Regex.DUE_PATTERN);
            setDescription(command, 9, index);
            ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, index);
            parsedCommand.addAll(parsedDateTime);
        } else {
            System.out.println(Messages.INVALID_DESC_DATE);
        }
    }

    public void setDescription(String command, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            String description = command.substring(startIndex, endIndex);
            parsedCommand.add(description);
        }
    }

    public void parseUpdate(String commandName, String command, String[] words) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            setIndex(words[1]);
        } else {
            System.out.println(Messages.INVALID_INDEX);
        }
    }

    public void setIndex(String index) {
        int taskIndex = Integer.parseInt(index);

        if (isValidIndex(taskIndex)) {
            parsedCommand.add(index);
        } else {
            parsedCommand.add("-1");
            System.out.println(Messages.INVALID_INDEX_VALUE
                    + Task.getListSize()
                    + "!");
        }
    }

    public boolean isValidIndex(int taskIndex) {
        return taskIndex <= Task.getListSize();
    }

    public void parseDefault(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
        }
    }

    public boolean isValidCommandFormat(String command) {
        return command.matches(Regex.TODO_COMMAND) || command.matches(Regex.EVENT_COMMAND) || command.matches(Regex.DEADLINE_COMMAND) ||
                command.matches(Regex.SELECT_INDEX_COMMAND) || command.matches(Regex.LIST_COMMAND) ||
                command.matches(Regex.REMOVE_INDEX_COMMAND) || command.matches(Regex.BYE_COMMAND);
    }
}
