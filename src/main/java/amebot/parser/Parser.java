package amebot.parser;

import amebot.common.Messages;
import amebot.common.Regex;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.util.EnumSet;
import java.util.ArrayList;

/**
 * Represents a parser that parses the user's input.
 */
public class Parser {
    private static final int START_INDEX_OF_TODO = 5;
    private static final int START_INDEX_OF_EVENT = 6;
    private static final int START_INDEX_OF_DEADLINE = 9;
    private static final int START_INDEX_OF_UPDATE_DATETIME = 9;
    private static final int START_INDEX_OF_UPDATE_DESCRIPTION = 10;
    protected ArrayList<String> parsedCommand = new ArrayList<>();

    /**
     * Parses the user's input and returns the parsed command.
     *
     * @param command The user's input.
     * @return The parsed command.
     */
    public ArrayList<String> parseCommand(String command) {
        int endIndex = command.length();
        String[] taskDetail = command.trim().split(" ");
        String commandName = taskDetail[0].toUpperCase();

        if (!isValidCommandType(commandName)) {
            System.out.println(Messages.INVALID_COMMAND);
        } else {
            Keyword commandType = Keyword.valueOf(commandName);

            switch (commandType) {
                case FIND:
                    parseFind(commandName, command, endIndex);
                    break;
                case TODO:
                    parseTodo(commandName, command, endIndex);
                    break;
                case EVENT:
                    parseEvent(commandName, command, endIndex);
                    break;
                case DEADLINE:
                    parseDeadline(commandName, command, endIndex);
                    break;
                case UPDATE:
                    parseUpdate(commandName, command, taskDetail, endIndex);
                    break;
                case MARK:
                    // Fallthrough
                case UNMARK:
                    // Fallthrough
                case REMOVE:
                    parseMarkStatusOrRemove(commandName, command, taskDetail);
                    break;
                case LIST:
                    // Fallthrough
                case BYE:
                    parseListOrBye(commandName, command);
                    break;
            }
        }

        return parsedCommand;
    }

    /**
     * Checks if the command type is valid.
     *
     * @param commandName The command name.
     * @return True if the command type is valid, false otherwise.
     */
    public boolean isValidCommandType(String commandName) {
        EnumSet<Keyword> commandList = EnumSet.allOf(Keyword.class);

        for (Keyword commandType : commandList) {
            boolean isMatch = commandType.toString().equalsIgnoreCase(commandName);

            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    /**
     * Parses the keyword to find matching tasks.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param endIndex    The end index of the command.
     */
    public void parseFind(String commandName, String command, int endIndex) {
        boolean isFind = command.matches(Regex.FIND_COMMAND);

        if (!isFind) {
            System.out.println(Messages.INVALID_FIND_KEYWORD);
            return;
        }

        parsedCommand.add(commandName);
        setDescription(command, START_INDEX_OF_TODO, endIndex);
    }

    /**
     * Parses the description of the ToDo task.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param endIndex    The end index of the command.
     */
    public void parseTodo(String commandName, String command, int endIndex) {
        boolean isToDo = command.matches(Regex.TODO_COMMAND);

        if (!isToDo) {
            System.out.println(Messages.INVALID_DESC);
            return;
        }

        parsedCommand.add(commandName);
        setDescription(command, START_INDEX_OF_TODO, endIndex);
    }

    /**
     * Parses the description and dateTime of the Event task.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param endIndex    The end index of the command.
     */
    public void parseEvent(String commandName, String command, int endIndex) {
        boolean isEvent = command.matches(Regex.EVENT_COMMAND);

        if (!isEvent) {
            System.out.println(Messages.INVALID_DESC_DATE);
            return;
        }

        parsedCommand.add(commandName);
        int startIndexOfFromDateTime = command.indexOf(Regex.FROM_PATTERN);
        setDescription(command, START_INDEX_OF_EVENT, startIndexOfFromDateTime);

        ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, startIndexOfFromDateTime, endIndex);
        parsedCommand.addAll(parsedDateTime);
    }

    /**
     * Parses the description and dateTime of the Deadline task.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param endIndex    The end index of the command.
     */
    public void parseDeadline(String commandName, String command, int endIndex) {
        boolean isDeadline = command.matches(Regex.DEADLINE_COMMAND);

        if (!isDeadline) {
            System.out.println(Messages.INVALID_DESC_DATE);
            return;
        }

        parsedCommand.add(commandName);
        int startIndexOfDueDateTime = command.indexOf(Regex.DUE_PATTERN);
        setDescription(command, START_INDEX_OF_DEADLINE, startIndexOfDueDateTime);

        ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, startIndexOfDueDateTime, endIndex);
        parsedCommand.addAll(parsedDateTime);
    }

    /**
     * Sets the description of the task.
     *
     * @param command    The user's input.
     * @param startIndex The start index of the description.
     * @param endIndex   The end index of the description.
     */
    public void setDescription(String command, int startIndex, int endIndex) {
        boolean isInValidIndex = startIndex > endIndex;

        if (isInValidIndex) {
            return;
        }

        String description = command.substring(startIndex, endIndex);
        parsedCommand.add(description);
    }

    /**
     * Parses the index and description/dateTime of the task to be updated.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param taskDetail  The user's input split into an array of task detail.
     * @param endIndex    The end index of the command.
     */
    public void parseUpdate(String commandName, String command, String[] taskDetail, int endIndex) {
        boolean isUpdate = command.matches(Regex.UPDATE_INDEX_COMMAND);

        if (!isUpdate) {
            System.out.println(Messages.INVALID_UPDATE);
            return;
        }

        int taskIndex = Integer.parseInt(taskDetail[1]);
        setCommandNameAndIndex(commandName, taskIndex);

        if (parsedCommand.isEmpty()) {
            return;
        }

        boolean isFromDateTime = command.contains(Regex.FROM_PATTERN);
        boolean isToDateTime = command.contains(Regex.TO_PATTERN);
        boolean isDueDateTime = command.contains(Regex.DUE_PATTERN);
        boolean isDateTime = (isFromDateTime && isToDateTime) || isDueDateTime;

        if (isDateTime) {
            ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, Parser.START_INDEX_OF_UPDATE_DATETIME, endIndex);
            parsedCommand.addAll(parsedDateTime);
        } else {
            setDescription(command, START_INDEX_OF_UPDATE_DESCRIPTION, endIndex);
        }
    }

    /**
     * Parses the index of the task to be mark/unmark/remove.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param taskDetail  The user's input split into an array of task detail.
     */
    public void parseMarkStatusOrRemove(String commandName, String command, String[] taskDetail) {
        boolean isMarkStatus = command.matches(Regex.MARK_INDEX_COMMAND);
        boolean isRemove = command.matches(Regex.REMOVE_INDEX_COMMAND);

        if (isMarkStatus || isRemove) {
            int taskIndex = Integer.parseInt(taskDetail[1]);
            setCommandNameAndIndex(commandName, taskIndex);
        } else {
            System.out.println(Messages.INVALID_INDEX);
        }
    }

    /**
     * Sets the command name and index of the task to be updated.
     *
     * @param commandName The command name.
     * @param taskIndex   The index of the task to be updated.
     */
    public void setCommandNameAndIndex(String commandName, int taskIndex) {
        boolean isValidIndex = taskIndex <= Task.getListSize();

        if (!isValidIndex) {
            System.out.println(Messages.INVALID_INDEX_VALUE
                    + Task.getListSize()
                    + "!");
            return;
        }

        parsedCommand.add(commandName);
        parsedCommand.add(String.valueOf(taskIndex));
    }

    /**
     * Parses the list or bye command.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     */
    public void parseListOrBye(String commandName, String command) {
        boolean isList = command.matches(Regex.LIST_COMMAND);
        boolean isBye = command.matches(Regex.BYE_COMMAND);

        if (isList || isBye) {
            parsedCommand.add(commandName);
        } else {
            System.out.println(Messages.INVALID_COMMAND);
        }
    }
}
