package duke.commands;

import java.util.List;

/**
 * Mark Task
 */
public class Mark extends Task {
    public static final String CMD = "mark";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Mark a task as completed ||"
            + "Syntax: e.g. mark <+index>";

    /**
     * Mark constructor
     * @param index integer position in list
     * @param tasklist list of tasks
     */
    public Mark(int index, List<Task> tasklist) {
        this.isForRecording = false;
        this.isForUpdateList = true;
        this.description = markTask(index, tasklist);
    }

    /**
     * Returns a string message to user
     * @param index integer position in list
     * @param tasklist list of tasks
     * @return string message to user
     */
    private String markTask(int index, List <Task> tasklist) {
        String msg;
        try {
            if (tasklist.get(index - 1).getIsDone()) {
                msg = "The item has already been marked as done!";
            } else {
                tasklist.get(index - 1).setIsDone(true);
                msg = "Nice! I've marked this task as done: "
                        + tasklist.get(index - 1).toString();
            }
        } catch (IndexOutOfBoundsException e) {
            msg = "Indicate a number within the list: " + tasklist.size();
        }
        return msg;
    }

    /**
     * String to return to user
     * @return string message to user
     */
    @Override
    public String toString() {
        return description;
    }
}
