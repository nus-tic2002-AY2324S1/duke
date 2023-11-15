package task;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description,LocalDateTime from,LocalDateTime to) {
        super(description);
        isDone = false;
        this.from = from;
        this.to = to;
        tag = "E";
    }
    public boolean isDone(){
        return isDone;
    }
    public void setDone(boolean change){
        this.isDone = change;
    }
    public String getFrom(){
        return printDate(from);
    }
    public String getTo(){
        return printDate(to);
    }
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(from:" + getFrom() + " to:" + getTo() + ")";
    }
    @Override
    public LocalDateTime startTime(){
        return from;
    }
    @Override
    public LocalDateTime endTime(){
        return to;
    }
}

