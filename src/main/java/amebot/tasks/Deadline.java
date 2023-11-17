package amebot.tasks;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected static final String DEADLINE = "[DEADLINE] ";
    protected String dueDateTime;

    public Deadline(boolean isMarked, String description, String dueDateTime) {
        super(isMarked, description);
        super.type = DEADLINE;
        this.dueDateTime = dueDateTime.toUpperCase();
    }

    /**
     * Returns information of the task.
     *
     * @return Information of the task.
     */
    @Override
    public String getTask() {
        return type + status + description + this.dueDateTime;
    }

    /**
     * Sets due date and time of the task.
     *
     * @param dueDateTime Due date and time of the task.
     */
    public void setDueDateTime(String dueDateTime) {
        this.dueDateTime = dueDateTime.toUpperCase();
    }

    /**
     * Returns due date and time of the task.
     *
     * @return Due date and time of the task.
     */
    public String getDueDateTime() {
        return this.dueDateTime;
    }
}
