package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final LocalDateTime from;
    protected final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String encode() {
        String encodedFrom = from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String encodedTo = to.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("E | %s | %s | %s -> %s", encodeIsDone(), description, encodedFrom, encodedTo);
    }

    @Override
    public String toString() {
        String formattedFrom = formatLocalDateTime(from);
        String formattedTo = formatRelativeLocalDateTime(from, to);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedFrom, formattedTo);
    }
}
