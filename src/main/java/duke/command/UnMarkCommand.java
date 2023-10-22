package duke.command;

public class UnMarkCommand extends MarkCommand {
    public static final String COMMAND_WORD = "unmark";
    public static final String EXAMPLE_USAGE = "Example of usage:\nunmark 1";
    public UnMarkCommand(){
        isMark = false;
    }
    @Override
    public String message() {
        return "OK, I've marked this task as not done yet:";
    }
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

}
