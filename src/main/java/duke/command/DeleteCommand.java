package duke.command;


public class DeleteCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String EXAMPLE_USAGE = "Example of usage:\ndelete 1";
    public DeleteCommand(){}

    @Override
    public String message() {
        return "Noted. I've removed this task:";
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
