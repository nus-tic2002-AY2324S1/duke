package duke.task;

public class Todos extends Task {
    private final TaskType taskType = TaskType.T;
    public Todos(String description) {
        super(description);
    }

    public TaskType getTaskType() {
        return taskType;
    }
    @Override
    public String getStatusIcon() {
        return "[" + taskType + "]" + super.getStatusIcon();
    }

}
