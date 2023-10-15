package Duke.Task;

public class DeadlineTask extends Task {
    protected String taskDueDate;

    public DeadlineTask(String taskName, String taskDueDate) {
        super('D', taskName);
        this.taskDueDate = taskDueDate;
    }

    public DeadlineTask(String taskName, boolean completed, String taskDueDate) {
        super('D', taskName, completed);
        this.taskDueDate = taskDueDate;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", getTaskDueDate());
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + getTaskDueDate();
    }

}
