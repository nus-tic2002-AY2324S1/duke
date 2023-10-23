package duke.task;

public class Events extends Task {
    private TaskType taskType = TaskType.E;
    public Events(String description){
        super(description);
    }

    public TaskType getTaskType() { return taskType; }
    public void setTaskType(TaskType taskType){ this.taskType = taskType; }

    @Override
    public String getStatusIcon() {
        return "[" + taskType + "]" + super.getStatusIcon();
    }
}
