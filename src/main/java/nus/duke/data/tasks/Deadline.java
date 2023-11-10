package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskOptionKey;

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
        this(description, by, isDone, null);
    }

    /**
     * Instantiates a new `Deadline` task with the provided description, deadline date and time,
     * completion status, and an optional afterOption.
     *
     * @param description The description of the task.
     * @param by          The deadline date and time. Must not be null.
     * @param isDone      The completion status of the task.
     * @param afterOption An optional afterOption associated with the task.
     *                    It represents additional data relevant to the task execution.
     *                    Use null if no afterOption is provided.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone, TaskAfterOption afterOption) {
        super(description, isDone, afterOption);

        assert by != null;

        this.by = by;
        addAttribute(0, TaskOptionKey.BY, () -> Optional.of(formatLocalDateTime(by)));
    }

    @Override
    public String getType() {
        return "D";
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        return super.equals(o) && by.equals(deadline.getBy());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {super.hashCode(), by});
    }

    @Override
    public String encode() {
        String encodedBy = by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String encodedAfter = encodeAfterOption();
        return String.format("%s | %s | %s | %s | %s", getType(), encodeIsDone(), description, encodedBy, encodedAfter);
    }
}
