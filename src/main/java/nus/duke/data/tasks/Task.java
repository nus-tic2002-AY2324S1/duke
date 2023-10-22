package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class Task {
    private static final String DATE_PATTERN_OUTPUT = "MMM dd yyyy";
    private static final String TIME_PATTERN_OUTPUT = "h:mma";
    private static final String DATETIME_PATTERN_OUTPUT = DATE_PATTERN_OUTPUT + " " + TIME_PATTERN_OUTPUT;
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this(description, false);
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected static String formatLocalDateTime(LocalDateTime input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN_OUTPUT, Locale.ROOT);
        return input.format(dateTimeFormatter);
    }

    protected static String formatRelativeLocalDateTime(LocalDateTime referenceTime, LocalDateTime input) {
        String pattern = referenceTime.toLocalDate().equals(input.toLocalDate())
                ? TIME_PATTERN_OUTPUT
                : DATETIME_PATTERN_OUTPUT;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ROOT);
        return input.format(dateTimeFormatter);
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String encode();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    protected String encodeIsDone() {
        return isDone ? "1" : "0";
    }
}
