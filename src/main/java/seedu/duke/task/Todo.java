package seedu.duke.task;

import seedu.duke.commands.Command;

/**
 * Represents a Todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(Command.TODO, "T", description);
    }

    /**
     * Returns the status message of the Todo task.
     * @param idx Index of the Todo task.
     * @return Status message of the Todo task.
     */
    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx);
    }
}
