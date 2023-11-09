package seedu.duke.task;

import seedu.duke.commands.CommandEnum;
import seedu.duke.helper.WonkyDateTime;

/**
 * Represents an event task that has a start and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {

    protected WonkyDateTime from;
    protected WonkyDateTime to;

    /**
     * Constructs the Event class.
     *
     * @param description Description of the event task.
     * @param from Start time of the event task.
     * @param to End time of the event task.
     */
    public Event(String description, WonkyDateTime from, WonkyDateTime to) {
        super(CommandEnum.EVENT, "E", description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the status message of the event task.
     *
     * @param idx Index of the event task.
     * @return Status message of the event task.
     */
    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx)
            + " | from: " + from.getStyledDateTimeStr()
            + " | to: " + to.getStyledDateTimeStr();
    }
}
