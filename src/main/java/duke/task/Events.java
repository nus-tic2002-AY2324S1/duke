package duke.task;
import java.time.LocalDateTime;

public class Events extends Task {
    private final TaskType taskType = TaskType.E;
    private LocalDateTime eventStartAt;
    private LocalDateTime eventEndAt;

    public Events(String description){
        super(description);
    }

    public Events(String desccription, LocalDateTime start, LocalDateTime end){
        super(desccription);
        this.eventStartAt = start;
        this.eventEndAt = end;
    }

    public TaskType getTaskType() { return taskType; }

    public LocalDateTime getEventStartAt(){ return eventStartAt; }
    public void setEventStartAt(LocalDateTime start){ this.eventStartAt = start; }
    public LocalDateTime getEventEndAt(){ return eventEndAt; }
    public void setEventEndAt(LocalDateTime end){ this.eventEndAt = end; }


    @Override
    public String getStatusIcon() {
        return "[" + taskType + "]" + super.getStatusIcon();
    }
}
