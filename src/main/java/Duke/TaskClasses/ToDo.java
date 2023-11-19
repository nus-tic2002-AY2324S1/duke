package Duke.TaskClasses;

import Duke.ExceptionClasses.IncompleteDataException;

public class ToDo extends Task {

    /**
     * Constructs a to-do task with the given description.
     *
     * @param description The description of the to-do task.
     *                    This constructor is used for adding new to-do tasks.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a to-do task with the given description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     * @throws IncompleteDataException If the task data is incomplete.
     * This constructor is used when loading to-do tasks from a saved file.
     */
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

