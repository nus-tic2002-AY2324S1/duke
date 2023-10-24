package duke.task;

import duke.exception.InvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Constructor of Event task take in description sets its abbreviation
     *
     * @param description The description for the Todo task.
     */
    public Event(String description) {
        super(description);
        setAbbreviation();
    }

    /**
     * Constructs an Event object with the given parameters.
     *
     * @param isDone      A boolean value indicating whether the event is done (true) or not done (false).
     * @param description The description of the event.
     * @param from        The starting date and time of the event (in LocalDateTime format).
     * @param to          The ending date and time of the event (in LocalDateTime format).
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        setAbbreviation();
        setDone(isDone);
        this.fromDateTime = from;
        this.toDateTime = to;
    }

    /**
     * Executes the task by displaying a response indicating that the task has been added.
     *
     * @throws InvalidArgumentException If the task encounters invalid arguments.
     */
    public void execute() throws InvalidArgumentException {
        displayTaskAddedResponse();
    }

    /**
     * Sets the abbreviation for the Event object.
     */
    public void setAbbreviation() {
        abbreviation = 'E';
    }

    /**
     * Overrides the toString method to provide task-specific details.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s (from: %s to: %s)", abbreviation, getStatusIcon(), dukeDescription,
                getFromDateTime(DATE_TIME_FORMAT_DISPLAY), getToDateTime(DATE_TIME_FORMAT_DISPLAY));
    }

    /**
     * Retrieves the starting date and time of the Event object.
     *
     * @return The starting date and time of the Event (in LocalDateTime format).
     */
    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Retrieves the ending date and time of the Event object.
     *
     * @return The ending date and time of the Event (in LocalDateTime format).
     */
    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    /**
     * Retrieves the formatted starting date and time of the Event object based on the specified pattern.
     *
     * @param pattern The pattern to format the date and time (e.g., "yyyy-MM-dd HH:mm").
     * @return The formatted starting date and time of the Event (as a String).
     */
    public String getFromDateTime(String pattern) {
        return fromDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Retrieves the formatted ending date and time of the Event object based on the specified pattern.
     *
     * @param pattern The pattern to format the date and time (e.g., "yyyy-MM-dd HH:mm").
     * @return The formatted ending date and time of the Event (as a String).
     */
    public String getToDateTime(String pattern) {
        return toDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
