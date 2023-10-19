package duke.commandsTask;

import duke.common.DateFormatter;

public class Event extends Task {
    public static final String CMD = "event";
    public static final String CMD_HELP = "Command [" + CMD + "] || "
            + "Create an event task with two dates ||"
            + "Syntax: e.g. event <text> /from <date> /to <date>";
    protected String from, to;
    public Event(boolean isDone, String description, String from, String to){
        super(description);
        setIsDone(isDone);
        this.from = DateFormatter.toDate(from);
        this.to = DateFormatter.toDate(to);
    }

    @Override
    public String toCode(){
        return CMD + " " + ((isDone) ? "-m-" : "") +  description + " /from " + from + " /to " + to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
