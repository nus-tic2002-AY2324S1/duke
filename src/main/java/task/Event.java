package task;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor of Event take in description , the 'isDone' status set to false and the tag is set to E
     *
     * @param description description of event
     * @param from /from LocalDateTime
     * @param to /to LocalDateTime
     */
    public Event(String description,LocalDateTime from,LocalDateTime to) {
        super(description);
        isDone = false;
        this.from = from;
        this.to = to;
        tag = "E";
    }

    /**
     *
     * @return string of /from LocalDateTime
     */
    public String getFrom(){
        return printDate(from);
    }

    /**
     *
     * @return string of /to LocalDateTime
     */
    public String getTo(){
        return printDate(to);
    }

    /**
     *
     * @return string format of event
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(from:" + getFrom() + " to:" + getTo() + ")";
    }

    /**
     *
     * @return /from LocalDateTime
     */
    @Override
    public LocalDateTime startTime(){
        return from;
    }

    /**
     *
     * @return /to LocalDateTime
     */
    @Override
    public LocalDateTime endTime(){
        return to;
    }
}

