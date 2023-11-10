package duke.commands;

/**
 * Bye task that exits the program
 */
public class Bye extends Task {
    public static final String CMD = "bye";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Exits the program \n"
            + "\tSyntax: Type \"bye\" to exit";

    /**
     * Bye constructor
     */
    public Bye() {
        this.isForRecording = false;
        this.isForUpdateList = false;
    }

    @Override
    public String toString() {
        return "exit";
    }

}
