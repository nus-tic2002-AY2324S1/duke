package nus.duke.data.tasks;

import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskOptionKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

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

    protected TaskAfterOption afterOption;

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
        assert description != null;

        this.description = description;
        this.isDone = isDone;
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

    protected static String formatAfterOption(TaskAfterOption afterOption) {
        assert afterOption != null;

        if (afterOption.isAfterTask()) {
            return String.valueOf(afterOption.getTaskNumber());
        } else if (afterOption.isAfterTime()) {
            return formatLocalDateTime(afterOption.getDateTime());
        }
        throw new IllegalArgumentException("Invalid task after option.");
    }

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

    public TaskAfterOption getAfterOption() {
        return afterOption;
    }

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
        throw new IllegalStateException("Invalid task after option.");
    }

    protected void addAttribute(TaskOptionKey attributeKey, AttributeValueGetter attributeValueGetter) {
        this.attributes.add(new AbstractMap.SimpleEntry<>(attributeKey, attributeValueGetter));
    }

    protected void addAttribute(int index, TaskOptionKey attributeKey, AttributeValueGetter attributeValueGetter) {
        this.attributes.add(index, new AbstractMap.SimpleEntry<>(attributeKey, attributeValueGetter));
    }

    /**
     * Encodes the task as a string for storage.
     *
     * @return The encoded representation of the task.
     */
    public abstract String encode();

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

    @FunctionalInterface
    protected interface AttributeValueGetter {
        Optional<String> get();
    }
}
