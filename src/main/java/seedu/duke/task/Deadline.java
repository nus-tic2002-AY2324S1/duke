package seedu.duke.task;

import seedu.duke.commands.Command;
import seedu.duke.commands.WonkyDateTime;

public class Deadline extends Task {

    protected WonkyDateTime by;

    public Deadline(String description, WonkyDateTime by) {
        super(Command.DEADLINE, "D", description);
        this.by = by;
    }

    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx) + " |   by: " + by.getStyledDateTimeStr();
    }
}