package duke.commands;

import java.util.List;

/**
 * List command task
 */
public class ListCmd extends Task {
    public static final String CMD = "list";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Generate a list of tasks ||"
            + "Syntax: e.g. list";

    /**
     * List constructor
     * @param tasklist list of tasks
     */
    public ListCmd(List <Task> tasklist) {
        this.isForRecording = false;
        this.isForUpdateList = false;
        this.description = getList(tasklist);
    }

    /**
     * Returns a string of tasks
     * @param tasklist list of tasks
     * @return string of tasks
     */
    private String getList(List<Task> tasklist) {
        String msg;
        if (tasklist.isEmpty()) {
            msg = "There are no tasks in your list.";
        } else {
            msg = "Here are the tasks in your list:";
            for (int i = 0; i < tasklist.size(); i++) {
                msg = msg + "\n" + (i + 1) + ". " + tasklist.get(i).toString();
            }
        }
        return msg;
    }

    /**
     * String to return to user
     * @return
     */
    @Override
    public String toString() {
        return getDescription();
    }
}
