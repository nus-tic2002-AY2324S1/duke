package Duke.Task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    protected LocalDateTime taskDueDate;

    public DeadlineTask(String taskName, LocalDateTime taskDueDate) {
        super('D', taskName);
        this.taskDueDate = taskDueDate;
    }

    public DeadlineTask(String taskName, boolean completed, LocalDateTime taskDueDate) {
        super('D', taskName, completed);
        this.taskDueDate = taskDueDate;
    }

    private String getTaskDueDate() {
        return dateTimetoString(taskDueDate);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", getTaskDueDate());
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + taskDueDate.toString();
    }

}
