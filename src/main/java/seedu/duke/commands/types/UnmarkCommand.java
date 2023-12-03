package seedu.duke.commands.types;

import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.task.Task;
import seedu.duke.task.WonkyManager;

/**
 * Represents a command to unmark a task as done.
 * Inherits from the CommandType abstract class.
 * Requires one argument, the index of the task to be unmarked.
 * Executes the command by setting the task's done status to false.
 * Throws DukeException if the argument is not a valid integer or if the task index is out of bounds.
 */
public class UnmarkCommand extends CommandType {

    private static final int ONE_ARGS = 1;

    public UnmarkCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        WonkyManager wonkyManager = WonkyManager.getInstance();
        WonkyLogger wonkyLogger = WonkyLogger.getInstance();
        List<String> argList = this.getArgList();
        List<Task> tasks = wonkyManager.getTasks();
        int taskIdx = 0;
        try {
            taskIdx = Integer.parseInt(argList.get(0)) - 1;
            assert taskIdx >= 0 : "taskIdx cannot be negative!";
            assert taskIdx < tasks.size() : "taskIdx cannot be larger than tasks size!";
            Task task = tasks.get(taskIdx);
            if (super.validateArgs(this, ONE_ARGS)) {
                task.setDone(false);
            }
        } catch (NumberFormatException e) {
            wonkyLogger.expectedInteger(argList.get(0));
        } catch (IndexOutOfBoundsException e) {
            wonkyLogger.unmarkTypo(taskIdx + 1);
        }
    }
}
