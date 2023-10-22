package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The `AbstractTask` class is an abstract base class for tasks in Duke.
 * It provides common properties and methods for tasks, such as description, completion status,
 * and date/time formatting.
 */
public abstract class AbstractTask {
    private static final String DATE_PATTERN_OUTPUT = "MMM dd yyyy";
    private static final String TIME_PATTERN_OUTPUT = "h:mma";
    private static final String DATETIME_PATTERN_OUTPUT = DATE_PATTERN_OUTPUT + " " + TIME_PATTERN_OUTPUT;

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Instantiates a new `AbstractTask` with the provided description.
     *
     * @param description The description of the task.
     */
    protected AbstractTask(String description) {
        this(description, false);
    }

    /**
     * Instantiates a new `AbstractTask` with the provided description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    protected AbstractTask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Formats a `LocalDateTime` object as a string.
     *
     * @param input The `LocalDateTime` object to format.
     * @return The formatted date and time string.
     */
    protected static String formatLocalDateTime(LocalDateTime input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN_OUTPUT, Locale.ROOT);
        return input.format(dateTimeFormatter);
    }

    /**
     * Formats a `LocalDateTime` object as a string relative to a reference time.
     *
     * @param referenceTime The reference time used to determine the format.
     * @param input         The `LocalDateTime` object to format.
     * @return The formatted date and time string.
     */
    protected static String formatRelativeLocalDateTime(LocalDateTime referenceTime, LocalDateTime input) {
        String pattern = referenceTime.toLocalDate().equals(input.toLocalDate())
                ? TIME_PATTERN_OUTPUT
                : DATETIME_PATTERN_OUTPUT;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ROOT);
        return input.format(dateTimeFormatter);
    }

    /**
     * Gets the completion status of the task.
     *
     * @return `true` if the task is completed; `false` otherwise.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done `true` if the task is completed; `false` otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets an icon representing the completion status of the task.
     *
     * @return "X" if the task is completed; a space (" ") if the task is not completed.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Encodes the task as a string for storage.
     *
     * @return The encoded representation of the task.
     */
    public abstract String encode();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Encodes the completion status as a string for storage.
     *
     * @return "1" if the task is completed; "0" if the task is not completed.
     */
    protected String encodeIsDone() {
        return isDone ? "1" : "0";
    }
}
