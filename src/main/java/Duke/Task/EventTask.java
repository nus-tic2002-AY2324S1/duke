package Duke.Task;

public class EventTask extends Task {
    private final String taskFrom;
    private final String taskTo;

    public EventTask(String taskName, String taskFrom, String taskTo) {
        super('E', taskName);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    public EventTask(String taskName, boolean completed, String taskFrom, String taskTo) {
        super('E', taskName, completed);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    public String getTaskFrom() {
        return taskFrom;
    }

    public String getTaskTo() {
        return taskTo;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", getTaskFrom(), getTaskTo());
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + getTaskFrom() + " | " + getTaskTo();
    }
}
