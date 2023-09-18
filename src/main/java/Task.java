public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone=false;
    }
    public String getName() {
        return description;
    }
    public String getDescription() {
        return description;
    }
    public void mark(){
        this.isDone = true;
    }
    public void markUnDone(){
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
