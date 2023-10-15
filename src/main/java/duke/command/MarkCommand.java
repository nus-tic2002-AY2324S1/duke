package duke.command;

public class MarkCommand extends IndexBaseCommand {
    protected boolean isMark;

    public MarkCommand(){
        this.isMark = true;
    }

    @Override
    public String message() {
        return "Nice! I've marked this task as done:";
    }

    public boolean isMark(){
        return isMark;
    }

}
