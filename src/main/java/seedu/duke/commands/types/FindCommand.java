package seedu.duke.commands.types;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.task.Task;
import seedu.duke.task.WonkyManager;

public class FindCommand extends CommandType {

    private final int ONE_ARGS = 1;

    public FindCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, ONE_ARGS)) {
            List<Task> tasks = WonkyManager.getInstance().getTasks();
            List<Task> foundTasks = new ArrayList<Task>();
            String searchStr = this.getArgList().get(0);
            for (Task task : tasks) {
                if (task.getDescription().contains(searchStr)) {
                    foundTasks.add(task);
                }
            }
            if (foundTasks.size() > 0) {
                WonkyLogger.getInstance().printFoundTasks(foundTasks, searchStr);
            } else {
                WonkyLogger.getInstance().noTasksFound(searchStr);
            }
        }
    }
    
}
