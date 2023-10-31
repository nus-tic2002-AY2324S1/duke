public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone=false;
    }
    public String getDescription() {
        return description;
    }
    public void mark(boolean markchange){

        this.isDone = markchange;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone(){return isDone;}
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

