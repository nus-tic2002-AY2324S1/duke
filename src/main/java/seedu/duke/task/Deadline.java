package seedu.duke.task;

import seedu.duke.commands.Command;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(Command.DEADLINE, "D", description);
        this.by = by.trim();
    }

    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx) + " |   by: " + by;
    }
}