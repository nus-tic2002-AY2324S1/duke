package duke.commands;

/**
 * Task abstract class
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean toRecord;
    protected boolean toUpdateList;

    /**
     * Task empty constructor
     */
    public Task() {

    }

    /**
     * Task constructor with description
     * @param description string description of task
     */
    public Task(String description) {
        setDescription(description);
        this.isDone = false;
        this.toRecord = true;
        this.toUpdateList = false;
    }

    /**
     * Returns if complete
     * @return X or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns string description
     * @return string description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns boolean if complete
     * @return boolean
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Change isDone to complete or incomplete
     * @param isDone boolean
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Change description of this task
     * @param description String description of task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns boolean if this task is to be written into storage
     * @return boolean
     */
    public boolean getToRecord() {
        return this.toRecord;
    }

    /**
     * Returns boolean if this task is to be added to tasklist
     * @return boolean
     */
    public boolean getToUpdateList() {
        return this.toUpdateList;
    }

    /**
     * Code to be written into storage
     * @return string
     */
    public String toCode() {
        return ((isDone) ? "m " : "") + description;
    }

    /**
     * String to return to user
     * @return string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
