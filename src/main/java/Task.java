public class Task {
    protected String description;
    protected  boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon(){
        return (isDone? "X":" ");
    }

    public void markAsDone(boolean done){
        this.isDone = done;
    }
}
