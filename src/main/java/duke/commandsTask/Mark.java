package duke.commandsTask;
import java.util.List;

public class Mark extends Task{
    public static final String CMD = "mark";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Mark a task as completed ||"
            + "Syntax: e.g. mark <+index>";

    public Mark(int index, List <Task> tasklist){
        this.toRecord = false;
        this.toUpdateList = true;
        this.description = markTask(index, tasklist);
    }

    private String markTask(int index, List <Task> tasklist){
        String msg;
        try{
            if (tasklist.get(index-1).getIsDone()){
                msg = "The item has already been marked as done!";
            } else {
                tasklist.get(index-1).setIsDone(true);
                msg = "Nice! I've marked this task as done: "
                        + tasklist.get(index-1).toString();
            }
        }
        catch (IndexOutOfBoundsException e){
            msg = "Indicate a number within the list: " + tasklist.size();
        }
        return msg;
    }

    @Override
    public String toString(){
        return description;
    }
}
