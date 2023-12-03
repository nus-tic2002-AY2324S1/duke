package seedu.duke.commands.types;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.task.WonkyManager;

/**
 * Represents a command to list all tasks in the task list.
 * Inherits from the abstract class CommandType.
 * Contains a static final integer ZERO_ARGS which represents the number of arguments this command takes.
 * Contains a constructor to create a ListCommand object.
 * Contains an execute method to execute the command.
 * If the number of arguments is valid, the execute method prints the list of tasks using the WonkyLogger class.
 */
public class ListCommand extends CommandType {

    private static final int ZERO_ARGS = 0;

    public ListCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, ZERO_ARGS)) {
            WonkyLogger.getInstance().printListCommand(
                WonkyManager.getInstance().getTasks()
            );
        }
    }
}
