package duke.commands;

import duke.common.DateFormatter;

/**
 * Event Task
 */
public class Event extends Task {
    public static final String CMD = "event";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Create an event task with two dates ||"
            + "Syntax: e.g. event <text> /from <date> /to <date>";
    protected String from;
    protected String to;

    /**
     * Event constructor
     * @param isDone boolean to mark task as complete
     * @param description string description
     * @param from string or date
     * @param to string or date
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        setIsDone(isDone);
        setFrom(from);
        setTo(to);
    }

    /**
     * Changes 'from' to the text inputted
     * @param from String text or date
     */
    public void setFrom(String from) {
        this.from = DateFormatter.toDate(from);
    }

    /**
     * Changes 'to' to the text inputted
     * @param to String text or date
     */
    public void setTo(String to) {
        this.to = DateFormatter.toDate(to);
    }


    /**
     * Code entry for storage
     * @return
     */
    @Override
    public String toCode() {
        return CMD + " " + ((isDone) ? "-m-" : "") + description + " /from " + from + " /to " + to;
    }

    /**
     * String entry to user
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
