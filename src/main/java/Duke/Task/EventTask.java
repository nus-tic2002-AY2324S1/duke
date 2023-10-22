package Duke.Task;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime taskFrom;
    private final LocalDateTime taskTo;

    public EventTask(String taskName, LocalDateTime taskFrom, LocalDateTime taskTo) {
        super('E', taskName);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    public EventTask(String taskName, boolean completed, LocalDateTime taskFrom, LocalDateTime taskTo) {
        super('E', taskName, completed);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    private String getTaskFrom() {
        return dateTimetoString(taskFrom);
    }

    private String getTaskTo() {
        return dateTimetoString(taskTo);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", getTaskFrom(), getTaskTo());
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + taskFrom.toString() + " | " + taskTo.toString();
    }
}
