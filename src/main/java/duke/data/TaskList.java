package duke.data;

import java.util.ArrayList;
import java.util.List;

import duke.commands.Task;
import duke.parser.Parser;
import duke.storage.StorageFile;

/**
 * TaskList class. Manages lists of tasks
 */
public class TaskList {
    private final List<Task> tasklist = new ArrayList<>();


    public TaskList() {

    }

    /**
     * Returns a string line for UI to present to user and records the command into duke.storage
     *
     * @param line input line from user
     * @param myStorage duke.storage file user has currently opened
     * @param toWrite indicator whether the command should be written into duke.storage
     * @return string message to present to user
     */
    public String processTask(String line, StorageFile myStorage, boolean toWrite) {
        Task task = Parser.parseCommand(line, tasklist);
        String msg;
        /* if ToRecord is TRUE, add Task and append into storage else pass message*/
        if (task.getForRecording()) {
            tasklist.add(task);
            msg = "Got it. I've added this task:\n"
                    + tasklist.get(tasklist.size() - 1).toString() + "\n"
                    + "Now you have " + tasklist.size() + " tasks in the list.";
            if (toWrite) {
                myStorage.append(task);
            }
        } else {
            msg = task.toString();
        }

        if (task.getForUpdateList()) {
            myStorage.write(tasklist);
        }

        return msg;
    }

}
