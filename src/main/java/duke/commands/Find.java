package duke.commands;

import java.util.List;

/**
 * Find task which returns a list of tasks
 */
public class Find extends Task {
    public static final String CMD = "find";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Find tasks with keyword in description ||"
            + "Syntax: e.g. find <text>";

    /**
     * Find constructor
     *
     * @param textToFind string of text to find
     * @param tasklist list of tasks to search from
     */
    public Find(String textToFind, List<Task> tasklist) {
        this.isForRecording = false;
        this.isForUpdateList = false;
        this.description = findText(textToFind, tasklist);
    }

    /**
     * Returns a string of tasks that matched
     *
     * @param textToFind string of text to find
     * @param tasklist lis of tasks to search from
     * @return string
     */
    private String findText(String textToFind, List<Task> tasklist) {
        String msg = "";
        if (tasklist.isEmpty()) {
            return "There are no tasks in your list.";
        }

        int n = 0;
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).getDescription().contains(textToFind.trim())) {
                if (n == 0) {
                    msg = "Here are the matching tasks in your list:";
                }
                msg = msg + "\n" + (n + 1) + ". " + tasklist.get(i).toString();
                n = n + 1;
            }
        }

        if (msg.isEmpty()) {
            msg = "There are no matching tasks in your list with the text: [" + textToFind + "]";
        }

        return msg;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
