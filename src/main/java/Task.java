public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
    }

    public String printItemWithStatus() {
        return getStatusIcon() + description;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void setDone(boolean bool) {
        this.isDone = bool;
    }
}
