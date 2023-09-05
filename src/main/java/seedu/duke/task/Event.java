package seedu.duke.task;

import seedu.duke.commands.Command;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(Command.EVENT, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusMsg() {
        return "[D]" + super.getStatusMsg() + " (from: " + from + " to: "  + to + ")";
    }
}