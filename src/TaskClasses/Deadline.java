package TaskClasses;

import ExceptionClasses.IncompleteDataException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws IncompleteDataException {
        super(description);
        if (by == null || by.isEmpty()) {
            throw new IncompleteDataException("Deadline 'by' information is missing");
        }
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) throws IncompleteDataException {
        super(description, isDone);
        if (by == null || by.isEmpty()) {
            throw new IncompleteDataException("Deadline 'by' information is missing");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toFileString() {
        String isDoneSymbol = getIsDone() ? "1" : "0";
        return "D | " + isDoneSymbol + " | " + description + " | " + by;
    }
}
