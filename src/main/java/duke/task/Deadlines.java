package duke.task;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    private final TaskType taskType = TaskType.D;
    private LocalDateTime deadline;
    public Deadlines(String description) {
        super(description);
    }
    public Deadlines(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }
    public TaskType getTaskType() {
        return taskType;
    }
    public LocalDateTime getDeadline(){ return deadline; }
    public void setDeadline(LocalDateTime deadline){ this.deadline = deadline; }
    @Override
    public String getStatusIcon() {
        return "["+ taskType +"]" + super.getStatusIcon();
    }

}
