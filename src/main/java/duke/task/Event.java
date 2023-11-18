package duke.task;

import duke.common.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that is scheduled for a specific data and time
 * It extends the Task class and adds specific behavior for tasks
 */
public class Event extends Task {
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;


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
        markAsDone(isDone);
        this.fromDateTime = from;
        this.toDateTime = to;
    }

    /**
     * Executes the task by displaying a response indicating that the task has been added.
     */
    public void execute() {
        displayProcessedTaskResponse(Message.MESSAGE_ADDED_TASK);
    }

    /**
     * Sets the abbreviation for the Event object.
     */
    public void setAbbreviation() {
        abbreviation = 'E';
    }

    /**
     * @return A formatted string representing the task with start and end date/time information.
     * @inheritDoc Returns a string representation of the task, including its abbreviation, status icon, description,
     * start date/time, and end date/time. Dates are formatted according to the specified display date/time format.
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

    /**
     * Updates the start time of the Event object.
     *
     * @param from The new start time to set for the Event object.
     */
    public void updateFrom(LocalDateTime from) {
        fromDateTime = from;
    }

    /**
     * Updates the end time of the Event object.
     *
     * @param to The new end time to set for the Event object.
     */
    public void updateTo(LocalDateTime to) {
        toDateTime = to;
    }
}
