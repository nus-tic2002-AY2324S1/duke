package duke.command;

import java.util.List;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends AddTaskCommand {

    /**
     * Constructs an `AddTodoCommand` with the specified task name.
     *
     * @param taskName The name of the todo task.
     */
    public AddTodoCommand(String taskName) {

        super(taskName);
    }

    /**
     * Executes the command by creating a `TodoTask` and adding it to the task list.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param display     The message display interface to show messages to the user.
     * @param taskList    The list of tasks to which the new todo task will be added.
     */
    @Override
    public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {
        assert !taskName.isEmpty() : "Task name can't be blank!";
        // Create a `TodoTask` with the specified name
        Task task = new TodoTask(taskName);
        // Add the created todo task to the task list
        addTask(fileStorage, display, taskList, task);
    }

}
