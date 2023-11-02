package seedu.duke.task;

import seedu.duke.commands.Command;
import seedu.duke.commands.WonkyDateTime;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    protected WonkyDateTime by;

    /**
     * Constructs the Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, WonkyDateTime by) {
        super(Command.DEADLINE, "D", description);
        this.by = by;
    }

    /**
     * Returns the status message of the task.
     *
     * Overrides the getStatusMsg method in the Task class.
     * @param idx The index of the task in the task list.
     * @return The status message of the task.
     */
    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx) + " |   by: " + by.getStyledDateTimeStr();
    }
}
