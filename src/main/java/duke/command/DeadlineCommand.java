package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DeadlineCommand class represents a command that handles deadline tasks.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String EXAMPLE_USAGE = COMMAND_WORD + ": Adds a task with a specified deadline to the task " +
            "list.\n" +
            "Parameter: DESCRIPTION /by DATE " + DATE_FORMAT_MESSAGE + "\n" +
            "Example of usage: " + COMMAND_WORD + " return book /by 02/12/2019";
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s/by\\s(?<byArgument>\\w.*)");

    /**
     * @param tasks           The TaskList containing the tasks to be managed.
     * @param ui              The user interface for displaying messages to the user.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     * @inheritDoc Executes the command to create a new deadline task and adds it to the task list.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        Deadline deadline = processDetail(keywordArgument);
        deadline.execute();
        tasks.add(deadline);
    }

    /**
     * Processes the details provided in the UserKeywordArgument object for creating a Deadline task.
     * Validates the command arguments, extracts necessary details, and constructs a Deadline object.
     *
     * @param keywordArgument The parsed user input containing the keyword and task details.
     * @return A Deadline object created based on the processed details.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    private Deadline processDetail(UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new DeadlineCommand());
        final Matcher byMatcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        validateArgumentMatcher(byMatcher, new DeadlineCommand(), "/by");

        final String description = byMatcher.group("description");
        final String byArgument = byMatcher.group("byArgument");
        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(byArgument);
        validateDateMatcher(dateMatcher, new DeadlineCommand(), "");

        LocalDateTime by = Parser.constructDateTime(dateMatcher);
        assert !description.isEmpty() : "The description cannot be empty";
        return new Deadline(false, description, by);
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
