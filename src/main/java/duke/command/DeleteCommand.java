package duke.command;


public class DeleteCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "DELETE";
    public DeleteCommand(){}

    @Override
    public String message() {
        return "Noted. I've removed this task:";
    }
}
