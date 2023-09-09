public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getIsDone(){
        return isDone ? "x" : " ";
    }

    public void setIsDone(boolean markUnmark){
        isDone = markUnmark;
    }
}
