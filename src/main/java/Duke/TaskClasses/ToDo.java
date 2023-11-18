package Duke.TaskClasses;

import Duke.ExceptionClasses.IncompleteDataException;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) throws IncompleteDataException {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        String isDoneSymbol = getIsDone() ? "1" : "0";
        return "T | " + isDoneSymbol + " | " + description;
    }
}

