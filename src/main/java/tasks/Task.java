package tasks;

public class Task {
    protected String description;
    protected boolean isDone = false;
    protected Priority p = Priority.LOW;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public Boolean isMarked() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTaskPriority(Priority p) {
        this.p = p;
    }

    public String getPriority() {
        String priority = this.p.name();
        String formatted = priority.substring(0, 1) + priority.substring(1).toLowerCase();
        return formatted.trim();
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + getPriority() + "] " + description;
    }

    public String writeToFile() {
        String text = p + "|" + isDone + "|" + description;
        return text;
    }
}
