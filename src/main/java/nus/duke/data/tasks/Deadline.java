package nus.duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

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
