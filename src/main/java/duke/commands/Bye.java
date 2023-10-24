package duke.commands;

/**
 * Bye task that exits the program
 */
public class Bye extends Task {
    public static final String CMD = "bye";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Exits the program ||"
            + "Type \"bye\" to exit";

    /**
     * Bye constructor
     */
    public Bye() {
        this.toRecord = false;
        this.toUpdateList = false;
    }

    @Override
    public String toString() {
        return "exit";
    }

}
