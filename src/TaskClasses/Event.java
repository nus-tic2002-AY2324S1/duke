package TaskClasses;

import ExceptionClasses.IncompleteDataException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws IncompleteDataException {
        super(description);
        if (from == null || from.isEmpty()) {
            throw new IncompleteDataException("Missing 'from' information in Event task");
        }
        if (to == null || to.isEmpty()) {
            throw new IncompleteDataException("Missing 'to' information in Event task");
        }
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) throws IncompleteDataException {
        super(description, isDone);
        if (from == null || from.isEmpty()) {
            throw new IncompleteDataException("Missing 'from' information in Event task");
        }
        if (to == null || to.isEmpty()) {
            throw new IncompleteDataException("Missing 'to' information in Event task");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        String isDoneSymbol = getIsDone() ? "1" : "0";
        return "E | " + isDoneSymbol + " | " + description + " | " + from + " | " + to;
    }
}
