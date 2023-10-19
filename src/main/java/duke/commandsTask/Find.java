package duke.commandsTask;

import java.util.List;

public class Find extends Task{
    public static final String CMD = "find";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Find tasks with keyword in description ||"
            + "Syntax: e.g. find <text>";

    public Find(String textToFind, List<Task> tasklist){
        this.toRecord = false;
        this.toUpdateList = false;
        this.description = findText(textToFind, tasklist);
    }

    private String findText (String textToFind, List<Task> tasklist){
        String msg = "";
        if (tasklist.isEmpty()) {
            return  "There are no tasks in your list.";
        }

        int n = 0;

        for (int i=0; i < tasklist.size(); i++){
            if (tasklist.get(i).getDescription().contains(textToFind.trim())) {
                if (n==0){msg = "Here are the matching tasks in your list:";}
                msg = msg + "\n" + (n+1) + ". " + tasklist.get(i).toString();
                n = n + 1;
            }
        }

        if (msg.isEmpty()) {
            msg = "There are no matching tasks in your list with the text: [" + textToFind + "]";
        }

        return msg;
    }

    @Override
    public String toString(){
        return getDescription();
    }
}
