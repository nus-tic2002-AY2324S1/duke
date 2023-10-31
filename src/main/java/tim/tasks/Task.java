package tim.tasks;
import java.io.Serializable;

/**
 * Task is an abstract class that is the parent class of ToDo, Deadline and Event.
 * It contains the description of the task, boolean of whether it is done and the type of task.
 */
public abstract class Task implements Serializable {
    private String description;
    private char type;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
        type = '-';
    }

    public String getDescription() { return this.description;}

    public String getIsDone(){
        return isDone ? "x" : " ";
    }

    public void setIsDone(boolean markUnmark){
        isDone = markUnmark;
    }

    public char getType (){
        return type;
    }

    public void setType (char type){ this.type = type; }
}
