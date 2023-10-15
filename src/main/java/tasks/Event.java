package tasks;

public class Event extends Task {
    protected final String EVENT = "[EVENT] ";
    protected String fromDateTime;
    protected String toDateTime;

    public Event(String description, String fromDateTime, String toDateTime) {
        super(description);
        super.type = EVENT;
        this.fromDateTime = fromDateTime.toUpperCase();
        this.toDateTime = toDateTime.toUpperCase();
    }

    @Override
    public String getTask() {
        return type + status + description + fromDateTime + toDateTime;
    }

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getToDateTime() {
        return toDateTime;
    }
}
