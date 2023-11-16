package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskOptionKey;

/**
 * The `AbstractTask` class is an abstract base class for tasks in Duke.
 * It provides common properties and methods for tasks, such as description, completion status,
 * and datetime formatting.
 */
public abstract class AbstractTask {
    private static final String DATE_PATTERN_OUTPUT = "MMM dd yyyy";
    private static final String TIME_PATTERN_OUTPUT = "h:mma";
    private static final String DATETIME_PATTERN_OUTPUT = DATE_PATTERN_OUTPUT + " " + TIME_PATTERN_OUTPUT;
    private static final String INVALID_TASK_AFTER_OPTION = "Invalid task after option.";

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * The task after option, indicating what this task should be scheduled after.
     */
    protected TaskAfterOption afterOption;

    /**
     * An array list to store attributes associated with the task, each represented as a key-value pair.
     * The attributes provide additional information or metadata about the task.
     */
    protected ArrayList<Map.Entry<TaskOptionKey, AttributeValueGetter>> attributes = new ArrayList<>();

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
        this(description, isDone, null);
    }

    /**
     * Instantiates a new `AbstractTask` with the provided description, completion status,
     * and an optional afterOption.
     *
     * @param description The description of the task. Must not be null.
     * @param isDone      The completion status of the task.
     * @param afterOption An optional afterOption associated with the task.
     *                    It represents additional data relevant to the task execution.
     *                    Use null if no afterOption is provided.
     */
    protected AbstractTask(String description, boolean isDone, TaskAfterOption afterOption) {
        assert description != null;

        this.description = description;
        this.isDone = isDone;
        this.afterOption = afterOption;
        addAttribute(TaskOptionKey.AFTER, this::getAfterOptionString);
    }

    /**
     * Formats a `LocalDateTime` object as a string.
     *
     * @param input The `LocalDateTime` object to format.
     * @return The formatted date and time string.
     */
    protected static String formatLocalDateTime(LocalDateTime input) {
        assert input != null;

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
        assert referenceTime != null;
        assert input != null;

        String pattern = referenceTime.toLocalDate().equals(input.toLocalDate())
            ? TIME_PATTERN_OUTPUT
            : DATETIME_PATTERN_OUTPUT;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ROOT);
        return input.format(dateTimeFormatter);
    }

    /**
     * Gets the type of the task.
     *
     * @return A string representing the type of the task.
     */
    public abstract String getType();

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
        assert description != null;

        this.description = description;
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
     * Gets the task after option associated with this task.
     *
     * @return The task after option.
     */
    public TaskAfterOption getAfterOption() {
        return afterOption;
    }

    /**
     * Sets the task after option for this task.
     *
     * @param afterOption The task after option to be set, specifying when this task should be scheduled in relation
     *                    to another task or a specific time.
     */
    public void setAfterOption(TaskAfterOption afterOption) {
        this.afterOption = afterOption;
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
     * Retrieves a string representation of the task after option, if available.
     *
     * @return An optional string representing the task after option.
     * @throws IllegalStateException if the task after option is invalid or unsupported.
     */
    public Optional<String> getAfterOptionString() {
        if (afterOption == null) {
            return Optional.empty();
        }
        if (afterOption.isAfterTask()) {
            return Optional.of(String.valueOf(afterOption.getTaskNumber()));
        }
        if (afterOption.isAfterTime()) {
            return Optional.of(formatLocalDateTime(afterOption.getDateTime()));
        }
        throw new IllegalStateException(INVALID_TASK_AFTER_OPTION);
    }

    /**
     * Adds a new attribute to the task, represented as a key-value pair, to provide additional information or metadata.
     *
     * @param attributeKey         The key for the attribute.
     * @param attributeValueGetter The function to get the attribute value.
     */
    protected void addAttribute(TaskOptionKey attributeKey, AttributeValueGetter attributeValueGetter) {
        assert attributeValueGetter != null;

        this.attributes.add(new AbstractMap.SimpleEntry<>(attributeKey, attributeValueGetter));
    }

    /**
     * Adds a new attribute to the task at a specified index, represented as a key-value pair, to provide additional
     * information or metadata.
     *
     * @param index                The index at which to add the attribute.
     * @param attributeKey         The key for the attribute.
     * @param attributeValueGetter The function to get the attribute value.
     */
    protected void addAttribute(int index, TaskOptionKey attributeKey, AttributeValueGetter attributeValueGetter) {
        assert attributeValueGetter != null;

        this.attributes.add(index, new AbstractMap.SimpleEntry<>(attributeKey, attributeValueGetter));
    }

    /**
     * Encodes the task as a string for storage.
     *
     * @return The encoded representation of the task.
     */
    public String encode() {
        String encodedAfter = encodeAfterOption();
        return String.format("%s | %s | %s | %s", getType(), encodeIsDone(), description, encodedAfter);
    }

    @Override
    public String toString() {
        StringBuilder attributesBuilder = new StringBuilder();
        if (!attributes.isEmpty()) {
            attributes.forEach(entry -> {
                TaskOptionKey attributeKey = entry.getKey();
                AttributeValueGetter attributeValueGetter = entry.getValue();
                Optional<String> optionalAttributeValue = attributeValueGetter.get();
                if (optionalAttributeValue.isEmpty()) {
                    return;
                }
                if (attributesBuilder.length() != 0) {
                    attributesBuilder.append(" ");
                }
                attributesBuilder.append(String.format("%s: %s", attributeKey, optionalAttributeValue.get()));
            });
        }
        String base = String.format("[%s][%s] %s", getType(), getStatusIcon(), getDescription());
        return attributesBuilder.length() > 0 ? String.format("%s (%s)", base, attributesBuilder) : base;
    }

    /**
     * Encodes the completion status as a string for storage.
     *
     * @return "1" if the task is completed; "0" if the task is not completed.
     */
    protected String encodeIsDone() {
        return isDone ? "1" : "0";
    }

    /**
     * Encodes the task after option as a string for storage.
     *
     * @return A string representation of the task after option.
     * @throws AssertionError if the task after option is invalid or unsupported.
     */
    protected String encodeAfterOption() {
        if (afterOption == null) {
            return "";
        }
        if (afterOption.isAfterTask()) {
            return String.valueOf(afterOption.getTaskNumber());
        }
        if (afterOption.isAfterTime()) {
            return afterOption.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        throw new AssertionError(INVALID_TASK_AFTER_OPTION);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AbstractTask)) {
            return false;
        }

        AbstractTask task = (AbstractTask) o;
        return description.equals(task.getDescription())
            && isDone == task.getDone()
            && (afterOption != null ? afterOption.equals(task.getAfterOption()) : task.getAfterOption() == null);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {description, isDone, afterOption});
    }

    /**
     * A functional interface to get an attribute's value as an optional string.
     */
    @FunctionalInterface
    protected interface AttributeValueGetter {
        /**
         * Gets the attribute value as an optional string.
         *
         * @return An optional string representing the attribute value.
         */
        Optional<String> get();
    }
}
