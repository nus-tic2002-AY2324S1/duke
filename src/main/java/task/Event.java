package task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public Event(String desc, boolean isDone, String from, String to) {
        super(desc, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | " + from + " | " + to;
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
