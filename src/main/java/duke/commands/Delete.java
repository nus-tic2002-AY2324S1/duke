package duke.commands;

import java.util.List;

/**
 * Delete Task
 */
public class Delete extends Task {
    public static final String CMD = "delete";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Delete the task in the list using the index ||"
            + "Syntax: e.g. delete <index(+ve)>";

    /**
     * Delete task constructor
     * @param index integer location in the list
     * @param tasklist array of tasks
     */
    public Delete(int index, List<Task> tasklist) {
        this.isForRecording = false;
        this.isForUpdateList = true;
        this.description = deleteTask(index, tasklist);
    }

    /**
     * Returns a string message when deleting a task
     * @param index integer location in the list
     * @param tasklist array of tasks
     * @return
     */
    private String deleteTask(int index, List<Task> tasklist) {
        String msg;
        try {
            String taskDetail = tasklist.get(index - 1).toString();
            tasklist.remove(index - 1);
            msg = "Noted. I've removed this task: "
                    + taskDetail
                    + "\nNow you have " + tasklist.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            msg = "Indicate a number within the list: " + tasklist.size();
        }
        return msg;
    }

    @Override
    public String toString() {
        return description;
    }
}
