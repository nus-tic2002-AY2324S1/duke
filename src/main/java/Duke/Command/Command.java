package Duke.Command;

import Duke.Duke;
import Duke.DukeExceptions.DukeException;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a base command in the Duke application.
 */
abstract class Command {

    /**
     * Executes the command.
     *
     * @throws DukeException If an error occurs while executing the command.
     */
    public abstract void execute() throws DukeException;

    /**
     * Stores the task list for future loading of tasks.
     */
    public void storeDuke() {
        List<Task> taskList = Duke.taskList;
        Duke.fileStorage.fileStorage(taskList);
    }
}

/**
 * Represents an `AddTaskCommand` that adds tasks to the Duke application.
 */
abstract class AddTaskCommand extends Command {

    // The name of the task to be added
    protected final String taskName;

    /**
     * Constructs an `AddTaskCommand` with the specified task name.
     *
     * @param taskName The name of the task to be added.
     */
    public AddTaskCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Adds a task to the task list and prompts the user with a message that the task has been successfully added.
     *
     * @param task The task to be added.
     */
    protected void addTask(Task task) {
        Duke.taskList.add(task);
        int itemIndex = Task.getTotalTasks() - 1;
        storeDuke();
        UserInterface.MessageDisplay.addedMessage(Duke.taskList, itemIndex);
    }
}

/**
 * Represents a command for checking tasks on a specific date.
 */
abstract class CheckTaskCommand {

    /**
     * Executes the command.
     *
     * @param checkedDate The date for which tasks should be checked.
     * @throws DukeException If an error occurs while executing the command.
     */
    public abstract void execute(LocalDate checkedDate) throws DukeException;
}
