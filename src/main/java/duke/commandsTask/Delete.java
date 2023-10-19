package duke.commandsTask;

import java.util.List;

public class Delete extends Task{
    public static final String CMD = "delete";
    public static final String CMD_HELP = "Command [" + CMD + "] || "
            + "Delete the task in the list using the index ||"
            + "Syntax: e.g. delete <index(+ve)>";

    public Delete(int index, List<Task> tasklist){
        this.toRecord = false;
        this.toUpdateList = true;
        this.description = deleteTask(index, tasklist);
    }

    private String deleteTask (int index, List<Task> tasklist){
        String msg;
        try{
            String taskDetail = tasklist.get(index-1).toString();
            tasklist.remove(index-1);
            msg = "Noted. I've removed this task: "
                    + taskDetail
                    + "\nNow you have " + tasklist.size() + " tasks in the list.";
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
