package seedu.duke.commands.types;

import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.utils.WonkyUtils;
import seedu.duke.task.Deadline;
import seedu.duke.task.WonkyManager;

/**
 * Represents a command to add a deadline task to the task list.
 * Inherits from the CommandType class.
 * Contains a static final integer DEADLINE_ARGS, which represents the number of arguments required for the command.
 * Contains static final integers DESC_IDX and BY_IDX,
 * which represent the indices of the description and by date arguments respectively.
 * Contains a constructor to create a new DeadlineCommand object with a given command and arguments.
 * Overrides the execute method to add a new Deadline task to the task list.
 */
public class DeadlineCommand extends CommandType {

    private static final int DEADLINE_ARGS = 2;
    private static final int DESC_IDX = 0;
    private static final int BY_IDX = 1;

    public DeadlineCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, DEADLINE_ARGS)) {
            List<String> argList = this.getArgList();
            WonkyManager.getInstance().addTask(
                new Deadline(
                    this.getArgList().get(DESC_IDX),
                    WonkyUtils.parseToDate(argList.get(BY_IDX).trim())
                )
            );
        }
    }
}
