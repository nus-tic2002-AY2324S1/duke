package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public boolean isDateInRange(LocalDate targetDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
        LocalDate byLdt = LocalDate.parse(by, formatter);
        return !targetDate.isBefore(byLdt) && !targetDate.isAfter(byLdt);
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
