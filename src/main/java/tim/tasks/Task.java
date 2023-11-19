package tim.tasks;
import java.io.Serializable;

/**
 * Represents a Task object.
 * Task is an abstract class that is the parent class of ToDo, Deadline and Event.
 * It contains the description of the task, boolean of whether it is done and the type of task.
 */
public abstract class Task implements Serializable {
    private String description;
    private char type;
    private boolean isDone;
    final static int ONE_WEEK = 7;

    /**
     * Creates a Task object
     *
     * @param description name of the task.
     */
    public Task(String description){
        this.description = description;
        isDone = false;
        type = '-';
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * Returns the status of the task.
     *
     * @return status of the task.
     */
    public String getIsDone(){

        return isDone ? "x" : " ";
    }

    /**
     * Sets IsDone state as mark or unmarked based on input.
     *
     * @param markUnmark boolean of whether the task is done.
     */
    public void setIsDone(boolean markUnmark){

        isDone = markUnmark;
    }

    /**
     * Returns the type of the task.
     *
     * @return type of the task.
     */
    public char getType (){

        return type;
    }

    /**
     * Sets the type of the task.
     *
     * @param type type of the task.
     */
    void setType (char type){

        this.type = type; }
}
