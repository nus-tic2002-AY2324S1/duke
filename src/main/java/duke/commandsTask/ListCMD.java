package duke.commandsTask;
import java.util.List;

public class ListCMD extends Task{
    public static final String CMD = "list";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Generate a list of tasks ||"
            + "Syntax: e.g. list";

    public ListCMD( List <Task> tasklist){
        this.toRecord = false;
        this.toUpdateList = false;
        this.description = getList(tasklist);
    }

    private String getList (List<Task> tasklist){
        String msg;
        if (tasklist.isEmpty()) {
            msg  = "There are no tasks in your list.";
        }
        else {
            msg = "Here are the tasks in your list:";
            for (int i=0; i < tasklist.size(); i++){
                msg = msg + "\n" + (i+1) + ". " + tasklist.get(i).toString();
            }
        }
        return msg;
    }

    @Override
    public String toString(){
        return getDescription();
    }
}
