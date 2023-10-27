package seedu.duke.task;

import seedu.duke.commands.Command;
import seedu.duke.commands.WonkyDateTime;

public class Event extends Task {

    protected WonkyDateTime from;
    protected WonkyDateTime to;

    public Event(String description, WonkyDateTime from, WonkyDateTime to) {
        super(Command.EVENT, "E", description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx) + " | from: " + from.getStyledDateTimeStr() + " | to: "  + to.getStyledDateTimeStr();
    }
}