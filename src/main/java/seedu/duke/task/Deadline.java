package seedu.duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusMsg() {
        return "[D]" + super.getStatusMsg() + " (by: " + by + ")";
    }
}