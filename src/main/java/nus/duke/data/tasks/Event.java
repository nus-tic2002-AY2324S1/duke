package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Event` class represents an event task with a specified start and end time in Duke.
 * It extends the `AbstractTask` class and includes both the event's start and end times.
 */
public class Event extends AbstractTask {
    /**
     * The start time of the event.
     */
    protected final LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected final LocalDateTime to;

    /**
     * Instantiates a new `Event` task with the provided description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    /**
     * Instantiates a new `Event` task with the provided description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
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
