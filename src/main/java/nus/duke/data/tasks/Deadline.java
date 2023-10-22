package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Deadline` class represents a task with a deadline in Duke.
 * It extends the `AbstractTask` class and includes a deadline date and time.
 */
public class Deadline extends AbstractTask {
    /**
     * The deadline date and time.
     */
    protected final LocalDateTime by;

    /**
     * Instantiates a new `Deadline` task with the provided description and deadline date and time.
     *
     * @param description The description of the task.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    /**
     * Instantiates a new `Deadline` task with the provided description, deadline date and time, and completion status.
     *
     * @param description The description of the task.
     * @param by          The deadline date and time.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets the deadline date and time.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String encode() {
        String encodedBy = by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("D | %s | %s | %s", encodeIsDone(), description, encodedBy);
    }

    @Override
    public String toString() {
        String formattedBy = formatLocalDateTime(by);
        return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
    }
}
