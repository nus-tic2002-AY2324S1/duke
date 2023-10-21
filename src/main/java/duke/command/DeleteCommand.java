package duke.command;


public class DeleteCommand extends IndexBaseCommand {
    public DeleteCommand(){}

    @Override
    public String message() {
        return "Noted. I've removed this task:";
    }

    @Override
    public String getCommandWord() {
        return "delete";
    }

    @Override
    public String getExampleUsage() {
        return "delete 1";
    }
}
