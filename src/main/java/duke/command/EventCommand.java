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

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String EXAMPLE_USAGE = "Example of usage:\nevent project meeting /from 02/12/2020 0800 /to " +
            "02/12/2020 1000\n" +
            DATE_TIME_FORMAT_MESSAGE;
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s+/from\\s+(?<from>\\w.*)" +
            "\\s+/to\\s+(?<to>\\w.*)");
    public static final String DATE_TIME_ERROR_MESSAGE = "OOPS!!! The 'from: date/time' can not be after the 'to: " +
            "date/time'";

    /**
     * Executes the command to create a new event task and adds it to the task list.
     *
     * @param tasks           The TaskList containing the tasks to be managed.
     * @param ui              The user interface for displaying messages to the user.
     * @param storage         The storage object used to store and load tasks.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        Event event = processDetail(keywordArgument);
        event.execute();
        tasks.add(event);
    }

    /**
     * Processes the details provided in the UserKeywordArgument object for creating an Event task.
     * Validates the command arguments, extracts necessary details, and constructs an Event object.
     * @param keywordArgument The parsed user input containing the keyword and task details.
     * @return An Event object created based on the processed details.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error message.
     */
    private Event processDetail(UserKeywordArgument keywordArgument) throws InvalidArgumentException{
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
        if (fromDateTime.isAfter(toDateTime)) {
            throw new InvalidArgumentException(Message.concat(DATE_TIME_ERROR_MESSAGE, EXAMPLE_USAGE));
        }

        return new Event(false, description, fromDateTime, toDateTime);
    }

    /**
     * Gets the example usage string for the command.
     *
     * @return The example usage string demonstrating how to use the command.
     */
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    /**
     * Gets the command word associated with the command.
     *
     * @return The command word representing the keyword for the command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
