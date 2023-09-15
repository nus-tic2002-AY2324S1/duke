package seedu.duke.task;

import seedu.duke.commands.Command;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(Command.EVENT, "E", description);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx) + " | from: " + from + " | to: "  + to;
    }
}