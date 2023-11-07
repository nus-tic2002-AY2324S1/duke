package amebot.tasks;

/**
 * Represents an abstract task.
 */
public abstract class Task {
    protected String type;
    protected String description;
    protected String status;
    protected static final String MARK = "[✓] ";
    protected static final String UNMARK = "[ ] ";
    protected static int listSize;

    /**
     * Creates a task.
     *
     * @param isMarked    Status of the task.
     * @param description Description of the task.
     */
    public Task(boolean isMarked, String description) {
        this.description = description.toUpperCase();

        if (isMarked) {
            status = MARK;
        } else {
            status = UNMARK;
        }

        listSize++;
    }

    public abstract String getTask();

    /**
     * Sets the type of task.
     *
     * @param type Type of task.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the type of task.
     *
     * @return Type of task.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets description of the task.
     *
     * @param description Description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets status of the task as marked.
     */
    public void setStatusAsMark() {
        this.status = MARK;
    }

    /**
     * Sets status of the task as unmarked.
     */
    public void setStatusAsUnmark() {
        this.status = UNMARK;
    }

    /**
     * Returns status of the task.
     *
     * @return Status of the task.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns size of the task list.
     *
     * @return Size of the task list.
     */
    public static int getListSize() {
        return listSize;
    }
}
