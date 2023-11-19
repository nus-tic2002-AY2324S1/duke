package amebot.tasks;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected static final String EVENT = "[EVENT] ";
    protected String fromDateTime;
    protected String toDateTime;

    public Event(boolean isMarked, String description, String fromDateTime, String toDateTime) {
        super(isMarked, description);
        super.type = EVENT;
        this.fromDateTime = fromDateTime.toUpperCase();
        this.toDateTime = toDateTime.toUpperCase();
    }

    /**
     * Returns information of the task.
     *
     * @return Information of the task.
     */
    @Override
    public String getTask() {
        return type + status + description + this.fromDateTime + this.toDateTime;
    }

    /**
     * Sets start date and time of the event.
     *
     * @param fromDateTime Start date and time of the event.
     */
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime.toUpperCase();
    }

    /**
     * Returns start date and time of the event.
     *
     * @return Start date and time of the event.
     */
    public String getFromDateTime() {
        return this.fromDateTime;
    }

    /**
     * Sets end date and time of the event.
     *
     * @param toDateTime End date and time of the event.
     */
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime.toUpperCase();
    }

    /**
     * Returns end date and time of the event.
     *
     * @return End date and time of the event.
     */
    public String getToDateTime() {
        return this.toDateTime;
    }
}
