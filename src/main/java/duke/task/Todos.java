package duke.task;

/**
 * This class represents a Todo task, which is a type of task without a specific deadline.
 * It extends the abstract Task class and provides specific methods and properties for Todo tasks.
 */
public class Todos extends Task {
    private final TaskType taskType = TaskType.T;

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todos(String description) {
        super(description);
    }


    /**
     * Gets the type of the task, which is 'T' for Todos.
     *
     * @return The task type.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Gets an icon representing the status of the Todo task.
     *
     * @return The status icon, which is "[T]" for Todo tasks, and [X] if the task is done, [ ] if it's not.
     */
    @Override
    public String getStatusIcon() {
        return "[" + taskType + "]" + super.getStatusIcon();
    }

}
