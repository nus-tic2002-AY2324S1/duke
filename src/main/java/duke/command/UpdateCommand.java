package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The `UpdateCommand` class represents a command to update an item by its index in a list.
 */
public class UpdateCommand extends IndexBaseCommand {
    private String description;
    private char abbreviation;
    private String key;
    private LocalDateTime by;
    private LocalDateTime from;
    private LocalDateTime to;

    public static final String COMMAND_WORD = "update";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Updates information for a task in the task " +
            "list.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Parameter : INDEX /desc DESCRIPTION | INDEX /by DATE " + DATE_FORMAT_MESSAGE + "\n" +
            "\t\t\t | INDEX /from " + DATE_TIME_FORMAT_MESSAGE + " | INDEX /to " + DATE_TIME_FORMAT_MESSAGE + "\n" +
            "Example of usage: \n" +
            COMMAND_WORD + " 1 /desc return book \t(for todo, deadline, event tasks)\n" +
            COMMAND_WORD + " 1 /by 23/02/2023 \t(only for deadline task)\n" +
            COMMAND_WORD + " 1 /from 23/11/2023 0300 \t(only for event task)\n" +
            COMMAND_WORD + " 1 /to 23/11/2023 0400 \t(only for event task)";
    public static final String UPDATE_ERROR_ARGUMENT = "OOPS!!! The argument format must adhere to the correct " +
            "pattern!";
    public static final String UPDATE_ERROR_BY = "OOPS!!! The /by keyword is only applicable to deadline tasks!";
    public static final String UPDATE_ERROR_FROM = "OOPS!!! The /from keyword is only applicable to event tasks!";
    public static final String UPDATE_ERROR_TO = "OOPS!!! The /to keyword is only applicable to event tasks!";
    public static final String UPDATE_TASK_MESSAGE = "Wonderful! I've updated this task with the latest changes.";
    public static final String DESC = "desc";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String BY = "by";
    private static final Pattern INDEX_ARGUMENT_FORMAT = Pattern.compile("(?<index>\\d+)\\s+/(?<key>desc|by|from|to)" +
            "\\s+(?<argument>\\w.*)");

    /**
     * Validates a user input argument against a specified format.
     *
     * @param keywordArgument The UserKeywordArgument object containing the user input argument.
     * @param taskList        The TaskList object used for retrieving task information.
     * @return True if the argument is valid, false otherwise.
     * @throws InvalidArgumentException If the argument is invalid or does not match the expected format.
     */
    public boolean isValidArgument(UserKeywordArgument keywordArgument, TaskList taskList) throws InvalidArgumentException {
        String indexArgument = keywordArgument.getArguments();

        validateArgument(isNullOrEmpty(indexArgument));

        final Matcher argumentMatcher = INDEX_ARGUMENT_FORMAT.matcher(indexArgument);
        if (!argumentMatcher.matches()) {
            throw new InvalidArgumentException(Message.concat(UPDATE_ERROR_ARGUMENT, getExampleUsage()));
        }

        setIndex(argumentMatcher.group("index"));
        key = argumentMatcher.group("key");
        String argument = argumentMatcher.group("argument");

        abbreviation = taskList.getAbbreviation(getIndex());

        return isValidateArgumentByType(argument);
    }

    /**
     * Validates the user input argument based on the task type abbreviation.
     *
     * @param argument The user input argument to validate.
     * @return True if the argument is valid, false otherwise.
     * @throws InvalidArgumentException If the argument is invalid for the corresponding task type.
     */
    private boolean isValidateArgumentByType(String argument) throws InvalidArgumentException {
        switch (abbreviation) {
        case 'T':
            return isValidTodoArgument(key, argument);
        case 'D':
            return isValidDeadlineArgument(key, argument);
        case 'E':
            return isValidEventArgument(key, argument);
        default:
            return false;
        }
    }

    /**
     * Validates user input arguments for an Event task.
     *
     * @param key      The argument key indicating the type of information being validated
     * @param argument The user input argument to validate.
     * @return True if the argument is valid, false otherwise.
     * @throws InvalidArgumentException If the argument is invalid for the corresponding key.
     */
    private boolean isValidEventArgument(String key, String argument) throws InvalidArgumentException {
        if (Objects.equals(key, DESC)) {
            description = argument;
            return true;
        }
        if (Objects.equals(key, BY)) {
            throw new InvalidArgumentException(UPDATE_ERROR_BY);
        }

        if (Objects.equals(key, FROM)) {
            from = parseDateTime(argument, key);
        } else if (Objects.equals(key, TO)) {
            to = parseDateTime(argument, key);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Parses a user input argument representing a date and time and constructs a LocalDateTime object.
     *
     * @param argument The user input argument representing a date and time to parse.
     * @param key      The key indicating the type of information being parsed
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws InvalidArgumentException If the input argument does not match the expected date and time format.
     */
    private LocalDateTime parseDateTime(String argument, String key) throws InvalidArgumentException {
        final Matcher dateMatcher = DATE_TIME_ARG_FORMAT.matcher(argument);
        validateDateMatcher(dateMatcher, new UpdateCommand(), key + " in");

        final Matcher timeMatcher = TIME_ARG_FORMAT.matcher(dateMatcher.group("timeArgument"));
        validateTimeMatcher(timeMatcher, new UpdateCommand(), key + " in");

        return Parser.constructDateTime(dateMatcher, timeMatcher);
    }

    /**
     * Validates user input arguments for a Deadline task.
     *
     * @param key      The argument key indicating the type of information being validated.
     * @param argument The user input argument to validate.
     * @return True if the argument is valid, false otherwise.
     * @throws InvalidArgumentException If the argument is invalid for the corresponding key.
     */
    private boolean isValidDeadlineArgument(String key, String argument) throws InvalidArgumentException {
        if (Objects.equals(key, DESC)) {
            description = argument;
            return true;
        }
        if (Objects.equals(key, FROM)) {
            throw new InvalidArgumentException(UPDATE_ERROR_FROM);
        }
        if (Objects.equals(key, TO)) {
            throw new InvalidArgumentException(UPDATE_ERROR_TO);
        }
        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(argument);
        validateDateMatcher(dateMatcher, new UpdateCommand(), "");
        if (dateMatcher.matches()) {
            by = Parser.constructDateTime(dateMatcher);
            return true;
        }
        return false;
    }

    /**
     * Validates user input arguments for a Todo task.
     *
     * @param key      The argument key indicating the type of information being validated
     * @param argument The user input argument to validate.
     * @return True if the argument is valid, false otherwise.
     * @throws InvalidArgumentException If the argument is invalid for the corresponding key.
     */
    private boolean isValidTodoArgument(String key, String argument) throws InvalidArgumentException {
        switch (key) {
        case BY:
            throw new InvalidArgumentException(UPDATE_ERROR_BY);
        case FROM:
            throw new InvalidArgumentException(UPDATE_ERROR_FROM);
        case TO:
            throw new InvalidArgumentException(UPDATE_ERROR_TO);
        default:
        }
        description = argument;
        return Objects.equals(key, DESC);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * @throws InvalidArgumentException If the input arguments are invalid or do not match the command requirements.
     * @inheritDoc Executes the specified command based on the user input arguments and updates the task list
     * accordingly.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if (!isValidArgument(keywordArgument, taskList)) {
            return;
        }
        validateIndexRange(getIndex());
        processCommand(taskList);
        Command command = Parser.parseKeywordToCommand(keywordArgument);
        processCommand(taskList, ui, command);
    }

    /**
     * Processes the command based on the user input arguments and updates the corresponding task in the task list.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @throws InvalidArgumentException If the user input arguments are invalid or if the command execution fails.
     */
    private void processCommand(TaskList taskList) throws InvalidArgumentException {
        switch (key) {
        case BY:
            taskList.updateDeadlineBy(getIndex(), by);
            break;
        case FROM:
            taskList.updateEventFrom(getIndex(), from);
            break;
        case TO:
            taskList.updateEventTo(getIndex(), to);
            break;
        default:
            taskList.updateDescription(getIndex(), description);
        }
    }

    /**
     * @return A string representing the message for a task.
     * @inheritDoc Retrieves the message indicating that a task has been updated.
     */
    @Override
    public String getMessage() {
        return UPDATE_TASK_MESSAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }
}
