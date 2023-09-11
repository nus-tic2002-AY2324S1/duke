public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
