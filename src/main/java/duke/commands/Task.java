package duke.commands;

/**
 * Task abstract class
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isForRecording;
    protected boolean isForUpdateList;

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
        this.isForRecording = true;
        this.isForUpdateList = false;
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
     * @param isDone boolean to indicate if the task is completed
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
    public boolean getForRecording() {
        return this.isForRecording;
    }

    /**
     * Returns boolean if this task is to be added to tasklist
     * @return boolean
     */
    public boolean getForUpdateList() {
        return this.isForUpdateList;
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
