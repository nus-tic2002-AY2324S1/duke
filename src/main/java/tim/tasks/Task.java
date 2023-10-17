package tim.tasks;
import java.io.Serializable;

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
