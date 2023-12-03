package seedu.duke.commands.types;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Todo;
import seedu.duke.task.WonkyManager;

/**
 * Represents a command to add a Todo task to the task list.
 * Inherits from the CommandType abstract class.
 * Requires one argument, which is the description of the Todo task.
 * Executes the command by adding the Todo task to the task list in the WonkyManager.
 */
public class TodoCommand extends CommandType {

    private static final int TODO_ARGS = 1;
    private static final int DESC_IDX = 0;

    public TodoCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, TODO_ARGS)) {
            WonkyManager.getInstance().addTask(new Todo(this.getArgList().get(DESC_IDX)));
        }
    }
}
