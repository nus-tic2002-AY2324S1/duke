package Duke.Command;

import Duke.Duke;
import Duke.DukeExceptions.DukeException;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;
import java.util.List;

abstract class Command {

    public abstract void execute() throws DukeException;

    public void storeDuke() {
        List<Task> taskList = Duke.taskList;
        Duke.fileStorage.fileStorage(taskList);
    }
}

abstract class AddTaskCommand extends Command {
    protected final String taskName;

    public AddTaskCommand(String taskName) {
        this.taskName = taskName;
    }

    protected void addTask(Task task) {
        Duke.taskList.add(task);
        int itemIndex = Task.getTotalTasks() - 1;
        storeDuke();
        UserInterface.MessageDisplay.addedMessage(Duke.taskList, itemIndex);
    }
}


