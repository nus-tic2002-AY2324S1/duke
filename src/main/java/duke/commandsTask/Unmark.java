package duke.commandsTask;
import java.util.List;

public class Unmark extends Task{
    public static final String CMD = "unmark";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Unmark a completed task ||"
            + "Syntax: e.g. unmark <+index>";

    public Unmark(int index, List <Task> tasklist){
        this.toRecord = false;
        this.toUpdateList = true;
        this.description = markTask(index, tasklist);
    }

    private String markTask(int index, List <Task> tasklist){
        String msg;
        try{
            if (tasklist.get(index-1).getIsDone()){
                tasklist.get(index-1).setIsDone(false);
                msg = "Ok, I've marked this task as not done yet: "
                        + tasklist.get(index-1).toString();
            } else {
                msg = "The item wasn't done";
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
