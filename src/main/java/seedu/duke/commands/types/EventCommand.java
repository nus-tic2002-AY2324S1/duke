package seedu.duke.commands.types;

import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.utils.WonkyUtils;
import seedu.duke.task.Event;
import seedu.duke.task.WonkyManager;

/**
 * Represents a command to add an event task to the task list.
 * Inherits from the CommandType abstract class.
 * Contains the execute method to add the event task to the task list.
 * Requires 3 arguments: description, start date/time, and end date/time.
 * Throws DukeException if the arguments are invalid.
 */
public class EventCommand extends CommandType {

    private static final int EVENT_ARGS = 3;
    private static final int DESC_IDX = 0;
    private static final int FROM_IDX = 1;
    private static final int TO_IDX = 2;

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
