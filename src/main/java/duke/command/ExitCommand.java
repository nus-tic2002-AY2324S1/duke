package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ExitCommand class represents a command that terminates the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String EXAMPLE_USAGE = "Example of usage:\nbye";

    public ExitCommand() {
    }

    /**
     * @param tasks           The TaskList containing the tasks to be managed.
     * @param ui              The user interface for displaying messages to the user.
     * @param storage         The storage object used to store and load tasks.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     * @inheritDoc Executes the exit command, sets the exit flag, and displays a goodbye message to the user.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateNonEmptyKeywordArgument(keywordArgument, new ExitCommand());
        setExit();
        ui.showResponseToUser(Message.MESSAGE_GOODBYE);
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
