package Duke.Task;

import java.time.LocalDateTime;

/**
 * Represents a `DeadlineTask` class that extends `Task` and represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime taskDueDate;

    /**
     * Constructs a `DeadlineTask` with the specified task name and due date.
     *
     * @param taskName    The name of the deadline task.
     * @param taskDueDate The due date and time of the deadline task.
     */
    public DeadlineTask(String taskName, LocalDateTime taskDueDate) {
        super('D', taskName);
        this.taskDueDate = taskDueDate;
    }

    /**
     * Constructs a `DeadlineTask` with the specified task name, completion status, and due date.
     *
     * @param taskName    The name of the deadline task.
     * @param completed   `true` if the task is completed, `false` otherwise.
     * @param taskDueDate The due date and time of the deadline task.
     */
    public DeadlineTask(String taskName, boolean completed, LocalDateTime taskDueDate) {
        super('D', taskName, completed);
        this.taskDueDate = taskDueDate;
    }

    /**
     * Gets the formatted due date of the task.
     *
     * @return The due date of the task as a formatted string.
     */
    private String getTaskDueDate() {
        return dateTimetoString(taskDueDate);
    }

    /**
     * Converts the `DeadlineTask` to a string for display.
     *
     * @return A string representation of the `DeadlineTask`.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", getTaskDueDate());
    }

    /**
     * Converts the `DeadlineTask` to a string for saving to Duke Data File.
     *
     * @return A string representation of the `DeadlineTask` for saving to Duke Data File.
     */
    @Override
    public String toFile() {
        return super.toFile() + " | " + taskDueDate.toString();
    }
}