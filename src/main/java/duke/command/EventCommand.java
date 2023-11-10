package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The EventCommand class represents a command that handles event tasks.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String EXAMPLE_USAGE = COMMAND_WORD + ": Adds an event to the task list with specified start " +
            "and end date-time.\n" +
            "Parameter: DESCRIPTION /from START-DATE " + DATE_TIME_FORMAT_MESSAGE + " /to END-DATE " + DATE_TIME_FORMAT_MESSAGE + "\n" +
            "Example of usage: " + COMMAND_WORD + " project meeting /from 02/12/2020 0800 /to 02/12/2020 1000";
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s+/from\\s+(?<from>\\w.*)" +
            "\\s+/to\\s+(?<to>\\w.*)");
    public static final String DATE_TIME_ERROR_MESSAGE = "OOPS!!! The 'from: date/time' can not be after the 'to: " +
            "date/time'";

    /**
     * @param tasks           The TaskList containing the tasks to be managed.
     * @param ui              The user interface for displaying messages to the user.*
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     * @inheritDoc Executes the command to create a new event task and adds it to the task list.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        Event event = processDetail(keywordArgument);
        event.execute();
        tasks.add(event);
    }

    /**
     * Processes the details provided in the UserKeywordArgument object for creating an Event task.
     * Validates the command arguments, extracts necessary details, and constructs an Event object.
     *
     * @param keywordArgument The parsed user input containing the keyword and task details.
     * @return An Event object created based on the processed details.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    private Event processDetail(UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new EventCommand());

        final Matcher matcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        validateArgumentMatcher(matcher, new EventCommand(), "/from & /to");

        final String description = matcher.group("description");
        final String from = matcher.group("from");
        final String to = matcher.group("to");

        final Matcher fromDateMatcher = DATE_TIME_ARG_FORMAT.matcher(from);
        validateDateMatcher(fromDateMatcher, new EventCommand(), "'from' in");

        final Matcher toDateMatcher = DATE_TIME_ARG_FORMAT.matcher(to);
        validateDateMatcher(toDateMatcher, new EventCommand(), "'to' in");

        final Matcher fromTimeMatcher = TIME_ARG_FORMAT.matcher(fromDateMatcher.group("timeArgument"));
        validateTimeMatcher(fromTimeMatcher, new EventCommand(), "'from' in");

        final Matcher toTimeMatcher = TIME_ARG_FORMAT.matcher(toDateMatcher.group("timeArgument"));
        validateTimeMatcher(toTimeMatcher, new EventCommand(), "'to' in");

        LocalDateTime fromDateTime = Parser.constructDateTime(fromDateMatcher, fromTimeMatcher);
        LocalDateTime toDateTime = Parser.constructDateTime(toDateMatcher, toTimeMatcher);
        validateDateTimeRange(fromDateTime, toDateTime);
        assert toDateTime.isAfter(fromDateTime) : "'From' date/time must be before 'To' date/time!";
        return new Event(false, description, fromDateTime, toDateTime);
    }

    /**
     * Validates the date and time range to ensure fromDateTime is before or equal to toDateTime.
     *
     * @param fromDateTime The start date and time to validate.
     * @param toDateTime   The end date and time to validate.
     * @throws InvalidArgumentException If fromDateTime is after toDateTime.
     */
    public static void validateDateTimeRange(LocalDateTime fromDateTime, LocalDateTime toDateTime) throws InvalidArgumentException {
        if (fromDateTime.isAfter(toDateTime)) {
            throw new InvalidArgumentException(Message.concat(DATE_TIME_ERROR_MESSAGE, EXAMPLE_USAGE));
        }
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
