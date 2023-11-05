package duke.command;

import java.util.List;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents an abstract `AddTaskCommand` that adds tasks to the Duke application.
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
     * Adds a task to the task list and prompts the user with a successfully added message.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param display     The message display interface to show messages to the user.
     * @param taskList    The list of tasks to which the new task will be added.
     * @param task        The task to be added.
     */
    protected void addTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                           Task task) {

        taskList.add(task);
        storeDuke(fileStorage, taskList);
        display.addedMessage(taskList);
    }

}
