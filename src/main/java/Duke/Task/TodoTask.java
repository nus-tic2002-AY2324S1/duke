package Duke.Task;

/**
 * Represents a `TodoTask` class for tasks in Duke.
 */
public class TodoTask extends Task {

    /**
     * Constructs a `TodoTask` with the specified task name, initializing it as not completed.
     *
     * @param taskName The name of the todo task.
     */
    public TodoTask(String taskName) {
        super('T', taskName);
    }

    /**
     * Constructs a `TodoTask` with the specified task name and completion status.
     *
     * @param taskName    The name of the todo task.
     * @param completed `true` if the todo task is completed, `false` otherwise.
     */
    public TodoTask(String taskName, boolean completed) {
        super('T', taskName, completed);
    }
}
