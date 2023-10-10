public class Events extends Task {
    private static final TaskType taskType = TaskType.E;
    public Events(String description){
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
