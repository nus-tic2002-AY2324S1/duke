package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public boolean isDateInRange(LocalDate targetDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
        LocalDate fromDateObj = LocalDate.parse(from, formatter);
        LocalDate toDateObj = LocalDate.parse(to, formatter);
        return !targetDate.isBefore(fromDateObj) && !targetDate.isAfter(toDateObj);
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
