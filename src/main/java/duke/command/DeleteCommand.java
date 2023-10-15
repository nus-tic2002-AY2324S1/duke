package duke.command;


public class DeleteCommand extends IndexBaseCommand {

    public DeleteCommand(){}

    @Override
    public String message() {
        return "Noted. I've removed this task:";
    }
}
