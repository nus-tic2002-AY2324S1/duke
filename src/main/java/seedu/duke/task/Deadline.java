package seedu.duke.task;

import seedu.duke.commands.Command;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(Command.DEADLINE, description);
        this.by = by;
    }

    @Override
    public String getStatusMsg() {
        return "[D]" + super.getStatusMsg() + " (by: " + by + ")";
    }
}