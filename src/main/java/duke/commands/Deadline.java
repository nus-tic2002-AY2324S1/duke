package duke.commands;

import duke.common.DateFormatter;

/**
 * Deadline Task
 */
public class Deadline extends Task {
    public static final String CMD = "deadline";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Create a task with a deadline with a single date ||"
            + "Syntax: e.g. deadline <text> /by <date>";
    protected String by;

    /**
     * Deadline constructor
     *
     * @param isDone boolean to indicate task is complete
     * @param description string description of task
     * @param by string or date
     */
    public Deadline(boolean isDone, String description, String by) {
        super(description);
        setIsDone(isDone);
        this.by = DateFormatter.toDate(by);
    }

    @Override
    public String toCode() {
        return CMD + " " + ((isDone) ? "-m-" : "") + description + " /by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
