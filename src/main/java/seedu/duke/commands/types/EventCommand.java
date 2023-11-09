package seedu.duke.commands.types;

import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.utils.WonkyUtils;
import seedu.duke.task.Event;
import seedu.duke.task.WonkyManager;

public class EventCommand extends CommandType {

    private final int EVENT_ARGS = 3;
    private final int DESC_IDX = 0;
    private final int FROM_IDX = 1;
    private final int TO_IDX = 2;

    public EventCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, EVENT_ARGS)) {
            List<String> argList = this.getArgList();
            WonkyManager.getInstance().addTask(
                new Event(
                    argList.get(DESC_IDX),
                    WonkyUtils.parseToDate(argList.get(FROM_IDX).trim()),
                    WonkyUtils.parseToDate(argList.get(TO_IDX).trim())
                )
            );
        }
    }
    
}
