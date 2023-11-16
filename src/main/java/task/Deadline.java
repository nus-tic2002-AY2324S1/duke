package task;
import java.time.LocalDateTime;

public class Deadline extends Todo {
    /**
     * /by value
     */
    protected LocalDateTime by;

    /**
     * Constructor of Deadline take in description , the 'isDone' status set to false and the tag is set to D
     *
     * @param description description of deadline
     * @param by local-datetime of /by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        tag = "D";
    }

    /**
     *
     * @return string of /by LocalDateTime
     */
    public String getBy() {
        return printDate(by);
    }

    /**
     *
     * @return String format of deadline
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + "(by:" + getBy() + ")";
    }

    /**
     *
     * @return /by LocalDateTime
     */
    @Override
    public LocalDateTime endTime(){
        return by;
    }

}

