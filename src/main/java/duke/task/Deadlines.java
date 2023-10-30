package duke.task;

import java.time.LocalDateTime;

/**
 * The `Deadlines` class represents a task with a specific deadline.
 */

public class Deadlines extends Task {
    private final TaskType taskType = TaskType.D;
    private LocalDateTime deadline;


    /**
     * Constructs a `Deadlines` object with the specified description.
     *
     * @param description The description of the deadline task.
     */
    public Deadlines(String description) {
        super(description);
    }

    /**
     * Constructs a `Deadlines` object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public Deadlines(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    /**
     * Gets the type of the task, which is 'D' for Deadlines.
     *
     * @return The task type.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task as a LocalDateTime.
     */
    public LocalDateTime getDeadline(){ return deadline; }

    /**
     * Sets the deadline of the task.
     *
     * @param deadline The new deadline to set.
     */
    public void setDeadline(LocalDateTime deadline){ this.deadline = deadline; }


    @Override
    public String getStatusIcon() {
        return "["+ taskType +"]" + super.getStatusIcon();
    }

}
