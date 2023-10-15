package duke.command;

public class UnMarkCommand extends MarkCommand {
    public UnMarkCommand(){
        isMark = false;
    }

    @Override
    public String message() {
        return "OK, I've marked this task as not done yet:";
    }
}
