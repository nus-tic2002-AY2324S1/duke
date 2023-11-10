package duke.commands;

/**
 * Todo Task class
 */
public class Todo extends Task {
    public static final String CMD = "todo";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Create a basic to do task\n"
            + "\tSyntax: e.g. todo <text>";

    /**
     * Todo constructor
     *
     * @param isdone boolean to indicate task is complete
     * @param description string description
     */
    public Todo(boolean isdone, String description) {
        super(description);
        setIsDone(isdone);
    }

    /**
     * Code to write into StorageFIle
     *
     * @return string code
     */
    @Override
    public String toCode() {
        return CMD + " " + ((isDone) ? "-m-" : "") + description;
    }

    /**
     * String to return to user
     *
     * @return strint message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
