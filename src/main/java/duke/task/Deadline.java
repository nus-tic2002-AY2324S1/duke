package duke.task;

import duke.common.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task that has a specific deadline.
 * It extends the Task class and adds specific behavior for tasks
 */
public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    /**
     * Constructs a Deadline object with the given parameters.
     *
     * @param isDone      A boolean value indicating whether the deadline task is done (true) or not done (false).
     * @param description The description of the deadline task.
     * @param byDateTime  The due date and time of the deadline task (in LocalDateTime format).
     */
    public Deadline(boolean isDone, String description, LocalDateTime byDateTime) {
        super(description);
        setAbbreviation();
        markAsDone(isDone);
        this.byDateTime = byDateTime;
    }

    /**
     * Executes the task by displaying a response indicating that the task has been added.
     */
    public void execute() {
        displayProcessedTaskResponse(Message.MESSAGE_ADDED_TASK);
    }

    /**
     * Sets the abbreviation for the Deadline object.
     */
    public void setAbbreviation() {
        abbreviation = 'D';
    }

    /**
     * @return A formatted string representing the task.
     * @inheritDoc Returns a string representation of the task, including its abbreviation, status icon, description,
     * and deadline.
     * The date is formatted according to the specified display date format.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s (by: %s)", abbreviation, getStatusIcon(), dukeDescription,
                getByDateTime(DATE_FORMAT_DISPLAY));
    }

    /**
     * Retrieves the due date and time of the Deadline object.
     *
     * @return The due date and time of the Deadline (in LocalDateTime format).
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    /**
     * Retrieves the formatted due date and time of the Deadline object based on the specified pattern.
     *
     * @param pattern The pattern to format the date and time (e.g., "yyyy-MM-dd HH:mm").
     * @return The formatted due date and time of the Deadline (as a String).
     */
    public String getByDateTime(String pattern) {
        return byDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Updates the deadline date and time of the object.
     *
     * @param by The new deadline date and time to set for the object.
     */
    public void updateBy(LocalDateTime by) {
        byDateTime = by;
    }
}
