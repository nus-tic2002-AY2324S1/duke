public class Deadlines extends Task {
    private static final TaskType taskType = TaskType.D;
    public Deadlines(String description) {
        super(description);
    }
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String getStatusIcon() {
        return "["+ taskType +"]" + super.getStatusIcon();
    }

}
