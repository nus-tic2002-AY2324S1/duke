import java.util.ArrayList;

public class Mark extends IndexBaseCommand{
    protected boolean isMark;
    private int index;

    public Mark(){
        this.isMark = true;
    }
    public Mark(String taskDescription){
        this.isMark = true;
        process(taskDescription);
    }

    @Override
    public String message() {
        return "Nice! I've marked this task as done:";
    }

    public boolean isMark(){
        return isMark;
    }
}
