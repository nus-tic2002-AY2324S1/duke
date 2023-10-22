package Duke.Command;

import Duke.Task.Task;
import Duke.Task.TodoTask;

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
     */
    @Override
    public void execute() {
        // Create a `TodoTask` with the specified name
        Task task = new TodoTask(taskName);
        // Add the created todo task to the task list
        addTask(task);
    }
}
