package duke.command;

public class MarkCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "mark";
    public static final String EXAMPLE_USAGE = "Example of usage:\nmark 1";
    protected boolean isMark;

    public MarkCommand(){
        this.isMark = true;
    }

    @Override
    public String message() {
        return "Nice! I've marked this task as done:";
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    public boolean isMark(){
        return isMark;
    }

}
