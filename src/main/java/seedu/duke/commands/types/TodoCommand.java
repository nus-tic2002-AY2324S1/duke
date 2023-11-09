package seedu.duke.commands.types;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Todo;
import seedu.duke.task.WonkyManager;

public class TodoCommand extends CommandType {

    private final int TODO_ARGS = 1;
    private final int DESC_IDX = 0;

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
