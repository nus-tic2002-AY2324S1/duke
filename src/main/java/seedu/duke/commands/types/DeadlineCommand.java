package seedu.duke.commands.types;

import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.utils.WonkyUtils;
import seedu.duke.task.Deadline;
import seedu.duke.task.WonkyManager;

public class DeadlineCommand extends CommandType {

    private final int DEADLINE_ARGS = 2;
    private final int DESC_IDX = 0;
    private final int BY_IDX = 1;

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
