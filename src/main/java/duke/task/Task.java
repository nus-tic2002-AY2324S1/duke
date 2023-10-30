package duke.task;

public abstract class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getType();

    public abstract String toFileString();
    
    public abstract String getPrintStatus();

    public void print() {
        System.out.println("[" + getType() + "]" + getPrintStatus());
    }
}
