package amebot.tasks;

public class Event extends Task {
    protected final String EVENT = "[EVENT] ";
    protected String fromDateTime;
    protected String toDateTime;

    public Event(boolean isSelected, String description, String fromDateTime, String toDateTime) {
        super(isSelected, description);
        super.type = EVENT;
        this.fromDateTime = fromDateTime.toUpperCase();
        this.toDateTime = toDateTime.toUpperCase();
    }

    @Override
    public String getTask() {
        return type + status + description + fromDateTime + toDateTime;
    }

    @Override
    public String getFromDateTime() {
        return fromDateTime;
    }

    @Override
    public String getToDateTime() {
        return toDateTime;
    }
}
