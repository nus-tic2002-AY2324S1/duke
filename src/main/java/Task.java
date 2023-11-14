public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
    }

    public String toDisplay() {
        return getStatusIcon() + getDescription();
    }

    public String toSave() {
        return getStatusValue() + getDescription();
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (getStatus() ? "[X] " : "[ ] ");
    }

    public String getStatusValue() {
        return (getStatus() ? "1 | " : "0 | ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }
}
