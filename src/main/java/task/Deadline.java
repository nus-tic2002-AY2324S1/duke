package task;

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

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
