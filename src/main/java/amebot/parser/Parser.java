package amebot.parser;

import amebot.commands.Command;
import amebot.commands.FindCommand;
import amebot.commands.AddCommand;
import amebot.commands.UpdateCommand;
import amebot.commands.MarkCommand;
import amebot.commands.UnmarkCommand;
import amebot.commands.DeleteCommand;
import amebot.commands.ListCommand;
import amebot.commands.ExitCommand;
import amebot.commands.InvalidCommand;
import amebot.common.Messages;
import amebot.common.Regex;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser that parses the user input command.
 */
public class Parser {
    private static final String DATE_TIME_INPUT_FORMAT = "[dd/MM/yyyy HHmm][yyyy-MM-dd HHmm]";
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT);
    private static final String DATE_TIME_OUTPUT_FORMAT = "dd MMM yyyy (EEE) h:mma";
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMAT);
    private static final String DATE_INPUT_FORMAT = "[dd/MM/yyyy][yyyy-MM-dd]";
    private static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);
    private static final String DATE_OUTPUT_FORMAT = "dd MMM yyyy (EEE)";
    private static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT);

    /**
     * Returns the parsed user input command.
     *
     * @param command User input command.
     * @return Parsed user input command.
     */
    public Command parseCommand(String command) {
        assert command != null : "Command should not be null!";
        int endIndex = command.length();
        String[] taskDetail = command.trim().split(" ");
        String commandName = taskDetail[0].toUpperCase();
        Keyword commandType;

        try {
            commandType = Keyword.valueOf(commandName);
        } catch (IllegalArgumentException err) {
            commandType = Keyword.INVALID;
        }

        switch (commandType) {
        case FIND:
            return parseFind(command, endIndex);
        case TODO:
            return parseTodo(command, endIndex);
        case EVENT:
            return parseEvent(command, endIndex);
        case DEADLINE:
            return parseDeadline(command, endIndex);
        case UPDATE:
            return parseUpdate(command, taskDetail, endIndex);
        case MARK:
            // Fallthrough
        case UNMARK:
            // Fallthrough
        case REMOVE:
            return parseMarkStatusOrRemove(commandName, command, taskDetail);
        case LIST:
            // Fallthrough
        case BYE:
            return parseListOrBye(commandName, command);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND);
        }
    }

    /**
     * Returns the parsed find command.
     *
     * @param command  User input command.
     * @param endIndex End index of the user input command.
     * @return Parsed find command.
     */
    public Command parseFind(String command, int endIndex) {
        boolean isFindCommand = command.matches(Regex.FIND_COMMAND);
        int startIndexOfFind = 5;
        boolean isValidIndex = startIndexOfFind < endIndex;

        if (!isFindCommand || !isValidIndex) {
            return new InvalidCommand(Messages.INVALID_FIND_KEYWORD);
        }

        String keyword = command.substring(startIndexOfFind, endIndex);

        return new FindCommand(keyword);
    }

    /**
     * Returns the parsed ToDo task.
     *
     * @param command  User input command.
     * @param endIndex End index of the user input command.
     * @return Parsed ToDo task.
     */
    public Command parseTodo(String command, int endIndex) {
        boolean isToDoCommand = command.matches(Regex.TODO_COMMAND);

        if (!isToDoCommand) {
            return new InvalidCommand(Messages.INVALID_DESC);
        }

        boolean isStatusMarked = false;
        int startIndexOfTodoDescription = 5;
        String description = command.substring(startIndexOfTodoDescription, endIndex);

        return new AddCommand(isStatusMarked, description);
    }

    /**
     * Returns the parsed Event task.
     *
     * @param command  User input command.
     * @param endIndex End index of the user input command.
     * @return Parsed Event task.
     */
    public Command parseEvent(String command, int endIndex) {
        boolean isEventCommand = command.matches(Regex.EVENT_COMMAND);

        if (!isEventCommand) {
            return new InvalidCommand(Messages.INVALID_DESC_DATE);
        }

        int startIndexOfDateTime = command.indexOf(Regex.FROM_PATTERN);
        String dateTime = command.substring(startIndexOfDateTime, endIndex);
        boolean isEventDateTimeFormat = dateTime.matches(Regex.FROM_PATTERN + Regex.DATE_TIME_PATTERN + Regex.TO_PATTERN + Regex.DATE_TIME_PATTERN);

        if (!isEventDateTimeFormat) {
            return new InvalidCommand(Messages.INVALID_DATE);
        }

        boolean isStatusMarked = false;
        int startIndexOfEventDescription = 6;
        String description = command.substring(startIndexOfEventDescription, startIndexOfDateTime);

        int startIndexOfFromDateTime = dateTime.indexOf(Regex.FROM_PATTERN) + 7;
        int endIndexOfFromDateTime = dateTime.indexOf(Regex.TO_PATTERN);
        int startIndexOfToDateTime = dateTime.indexOf(Regex.TO_PATTERN) + 5;
        int endIndexOfToDateTime = dateTime.length();
        String trimmedFromDateTime = dateTime.substring(startIndexOfFromDateTime, endIndexOfFromDateTime);
        String[] splitFromDateTime = trimmedFromDateTime.split(" ");
        String trimmedToDateTime = dateTime.substring(startIndexOfToDateTime, endIndexOfToDateTime);
        String[] splitToDateTime = trimmedToDateTime.split(" ");
        boolean isDateFormat = splitFromDateTime.length == 1 && splitToDateTime.length == 1;

        return parseEventDetails(isStatusMarked, description, trimmedFromDateTime, trimmedToDateTime, isDateFormat);
    }

    /**
     * Returns the parsed description, date and time of the Event task.
     *
     * @param isStatusMarked      Status of the task.
     * @param description         Description of the task.
     * @param trimmedFromDateTime Trimmed start date and time of the task.
     * @param trimmedToDateTime   Trimmed end date and time of the task.
     * @param isDateFormat        Format of the date and time.
     * @return Parsed description, date and time of the Event task.
     */
    public Command parseEventDetails(boolean isStatusMarked, String description, String trimmedFromDateTime, String trimmedToDateTime, boolean isDateFormat) {
        LocalDate localDateOfFrom;
        LocalDate localDateOfTo;
        LocalDateTime localDateTimeOfFrom;
        LocalDateTime localDateTimeOfTo;
        boolean isDateTimeRangeValid = false;
        String fromDateTimeOutput = "";
        String toDateTimeOutput = "";

        try {
            if (isDateFormat) {
                localDateOfFrom = LocalDate.parse(trimmedFromDateTime, DATE_INPUT_FORMATTER);
                localDateOfTo = LocalDate.parse(trimmedToDateTime, DATE_INPUT_FORMATTER);
                isDateTimeRangeValid = localDateOfFrom.isBefore(localDateOfTo);
                fromDateTimeOutput = localDateOfFrom.format(DATE_OUTPUT_FORMATTER);
                toDateTimeOutput = localDateOfTo.format(DATE_OUTPUT_FORMATTER);
            } else {
                localDateTimeOfFrom = LocalDateTime.parse(trimmedFromDateTime, DATE_TIME_INPUT_FORMATTER);
                localDateTimeOfTo = LocalDateTime.parse(trimmedToDateTime, DATE_TIME_INPUT_FORMATTER);
                isDateTimeRangeValid = localDateTimeOfFrom.isBefore(localDateTimeOfTo);
                fromDateTimeOutput = localDateTimeOfFrom.format(DATE_TIME_OUTPUT_FORMATTER);
                toDateTimeOutput = localDateTimeOfTo.format(DATE_TIME_OUTPUT_FORMATTER);
            }

            if (!isDateTimeRangeValid) {
                return new InvalidCommand(Messages.INVALID_DATE_TIME);
            }

            String fromDateTime = " (from: " + fromDateTimeOutput;
            String toDateTime = " to: " + toDateTimeOutput + ")";

            return new AddCommand(isStatusMarked, description, fromDateTime, toDateTime);
        } catch (Exception err) {
            return new InvalidCommand(Messages.INVALID_DATE_TIME_RANGE);
        }
    }

    /**
     * Returns the parsed Deadline task.
     *
     * @param command  User input command.
     * @param endIndex End index of the user input command.
     * @return Parsed Deadline task.
     */
    public Command parseDeadline(String command, int endIndex) {
        boolean isDeadlineCommand = command.matches(Regex.DEADLINE_COMMAND);

        if (!isDeadlineCommand) {
            return new InvalidCommand(Messages.INVALID_DESC_DATE);
        }

        int startIndexOfDateTime = command.indexOf(Regex.DUE_PATTERN);
        String dateTime = command.substring(startIndexOfDateTime, endIndex);
        boolean isDeadlineDateTimeFormat = dateTime.matches(Regex.DUE_PATTERN + Regex.DATE_TIME_PATTERN);

        if (!isDeadlineDateTimeFormat) {
            return new InvalidCommand(Messages.INVALID_DATE);
        }

        boolean isStatusMarked = false;
        int startIndexOfDeadlineDescription = 9;
        String description = command.substring(startIndexOfDeadlineDescription, startIndexOfDateTime);

        int startIndexOfDueDateTime = dateTime.indexOf(Regex.DUE_PATTERN) + 6;
        int endIndexOfToDateTime = dateTime.length();
        String trimmedDueDateTime = dateTime.substring(startIndexOfDueDateTime, endIndexOfToDateTime);
        String[] splitDueDateTime = trimmedDueDateTime.split(" ");
        boolean isDateFormat = splitDueDateTime.length == 1;

        return parseDeadlineDetails(isStatusMarked, description, trimmedDueDateTime, isDateFormat);
    }

    /**
     * Returns the parsed description, date and time of the Deadline task.
     *
     * @param isStatusMarked     Status of the task.
     * @param description        Description of the task.
     * @param trimmedDueDateTime Trimmed date and time of the task.
     * @param isDateFormat       Format of the date and time.
     * @return Parsed description, date and time of the Deadline task.
     */
    public Command parseDeadlineDetails(boolean isStatusMarked, String description, String trimmedDueDateTime, boolean isDateFormat) {
        LocalDate localDateOfDue;
        LocalDateTime localDateTimeOfDue;
        String dueDateTimeOutput = "";

        try {
            if (isDateFormat) {
                localDateOfDue = LocalDate.parse(trimmedDueDateTime, DATE_INPUT_FORMATTER);
                dueDateTimeOutput = localDateOfDue.format(DATE_OUTPUT_FORMATTER);
            } else {
                localDateTimeOfDue = LocalDateTime.parse(trimmedDueDateTime, DATE_TIME_INPUT_FORMATTER);
                dueDateTimeOutput = localDateTimeOfDue.format(DATE_TIME_OUTPUT_FORMATTER);
            }

            String dueDateTime = " (due: " + dueDateTimeOutput + ")";

            return new AddCommand(isStatusMarked, description, dueDateTime);
        } catch (Exception err) {
            return new InvalidCommand(Messages.INVALID_DATE_TIME_RANGE);
        }
    }

    /**
     * Returns the parsed update command.
     *
     * @param command    User input command.
     * @param taskDetail Task detail of the user input command.
     * @param endIndex   End index of the user input command.
     * @return Parsed update command.
     */
    public Command parseUpdate(String command, String[] taskDetail, int endIndex) {
        boolean isUpdateDateTimeCommand = command.matches(Regex.UPDATE_DATE_TIME_COMMAND);
        boolean isUpdateDescriptionCommand = command.matches(Regex.UPDATE_DESCRIPTION_COMMAND);

        if (!isUpdateDateTimeCommand && !isUpdateDescriptionCommand) {
            return new InvalidCommand(Messages.INVALID_INDEX_VALUE
                    + Task.getListSize()
                    + "!");
        }

        int taskIndex = Integer.parseInt(taskDetail[1]);
        assert taskIndex > 0 : "Task index refers to item number and should be greater than 0!";
        boolean isValidIndex = taskIndex <= Task.getListSize();

        if (!isValidIndex) {
            return new InvalidCommand(Messages.INVALID_INDEX_VALUE
                    + Task.getListSize()
                    + "!");
        }

        String description = "";
        String fromDateTime = "";
        String toDateTime = "";
        String dueDateTime = "";
        boolean isFromFormat = command.contains(Regex.FROM_PATTERN);
        boolean isToFormat = command.contains(Regex.TO_PATTERN);

        if (isUpdateDateTimeCommand) {
            int startIndexOfUpdateDateTime;
            String dateTime = "";

            if (isFromFormat) {
                startIndexOfUpdateDateTime = command.indexOf(Regex.FROM_PATTERN) + 7;
                dateTime = command.substring(startIndexOfUpdateDateTime, endIndex);
            } else if (isToFormat) {
                startIndexOfUpdateDateTime = command.indexOf(Regex.TO_PATTERN) + 5;
                dateTime = command.substring(startIndexOfUpdateDateTime, endIndex);
            } else {
                startIndexOfUpdateDateTime = command.indexOf(Regex.DUE_PATTERN) + 6;
                dateTime = command.substring(startIndexOfUpdateDateTime, endIndex);
            }

            String[] splitDateTime = dateTime.split(" ");
            boolean isDateFormat = splitDateTime.length == 1;

            LocalDate localDate;
            LocalDateTime localDateTime;
            String dateTimeOutput = "";

            try {
                if (isDateFormat) {
                    localDate = LocalDate.parse(dateTime, DATE_INPUT_FORMATTER);
                    dateTimeOutput = localDate.format(DATE_OUTPUT_FORMATTER);
                } else {
                    localDateTime = LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMATTER);
                    dateTimeOutput = localDateTime.format(DATE_TIME_OUTPUT_FORMATTER);
                }
            } catch (Exception err) {
                return new InvalidCommand(Messages.INVALID_DATE_TIME_RANGE);
            }

            if (isFromFormat) {
                fromDateTime = " (from: " + dateTimeOutput;
            } else if (isToFormat) {
                toDateTime = " to: " + dateTimeOutput + ")";
            } else {
                dueDateTime = " (due: " + dateTimeOutput + ")";
            }
        } else {
            int startIndexOfUpdateDescription = 8 + taskDetail[1].length();
            description = command.substring(startIndexOfUpdateDescription, endIndex);
        }

        return new UpdateCommand(taskIndex, description, fromDateTime, toDateTime, dueDateTime);
    }

    /**
     * Returns the parsed mark or unmark or remove command.
     *
     * @param commandName Command name.
     * @param command     User input command.
     * @param taskDetail  Task detail of the user input command.
     * @return Parsed mark or unmark or remove command.
     */
    public Command parseMarkStatusOrRemove(String commandName, String command, String[] taskDetail) {
        boolean isMarkCommand = command.matches(Regex.MARK_INDEX_COMMAND);
        boolean isRemoveCommand = command.matches(Regex.REMOVE_INDEX_COMMAND);

        if ((!isMarkCommand && !isRemoveCommand)) {
            return new InvalidCommand(Messages.INVALID_INDEX
                    + Task.getListSize()
                    + "!");
        }

        int taskIndex = Integer.parseInt(taskDetail[1]);
        assert taskIndex > 0 : "Task index refers to item number and should be greater than 0!";
        boolean isValidIndex = taskIndex <= Task.getListSize();

        if (!isValidIndex) {
            return new InvalidCommand(Messages.INVALID_INDEX_VALUE
                    + Task.getListSize()
                    + "!");
        }

        if (commandName.matches(Keyword.MARK.toString())) {
            return new MarkCommand(taskIndex);
        } else if (commandName.matches(Keyword.UNMARK.toString())) {
            return new UnmarkCommand(taskIndex);
        } else {
            return new DeleteCommand(taskIndex);
        }
    }

    /**
     * Returns the parsed list or bye command.
     *
     * @param commandName Command name.
     * @param command     User input command.
     * @return Parsed list or bye command.
     */
    public Command parseListOrBye(String commandName, String command) {
        boolean isListCommand = command.matches(Regex.LIST_COMMAND);
        boolean isByeCommand = command.matches(Regex.BYE_COMMAND);

        if (!isListCommand && !isByeCommand) {
            return new InvalidCommand(Messages.INVALID_COMMAND);
        }

        if (commandName.matches(Keyword.LIST.toString())) {
            return new ListCommand();
        } else {
            return new ExitCommand();
        }
    }
}
