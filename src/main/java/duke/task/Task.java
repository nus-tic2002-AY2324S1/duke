package duke.task;


/**
 * This abstract class represents a task and defines common properties and methods for all tasks.
 * Subclasses should implement specific task types like Todo, Deadline, and Event.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;


    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String Desc){ this.description = Desc; }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription(){ return this.description; }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false if it's not.
     */
    public boolean getStatus(){
        return isDone;
    }


    /**
     * Marks the task as done.
     */
    public void markAsDone(){ this.isDone = true; }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone(){ this.isDone = false; }

    /**
     * Gets an icon representing the status of the task.
     *
     * @return The status icon, which is "[X]" for done tasks and "[ ]" for undone tasks.
     */
    public String getStatusIcon() {  return isDone? "[X]" : "[ ]"; }

    /**
     * Gets the type of the task, such as 'T' for Todo, 'D' for Deadline, or 'E' for Event.
     *
     * @return The task type.
     */
    public abstract TaskType getTaskType();
}