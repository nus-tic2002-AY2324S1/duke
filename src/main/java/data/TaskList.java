package data;

import commandsTask.*;

import java.util.List;
import java.util.ArrayList;
import parser.Parser;
import storage.StorageFile;

public class TaskList {
    private final List<Task> tasklist = new ArrayList<>();

    public TaskList () {}

    /**
     * Returns a string line for UI to present to user and records the command into storage
     *
     * @param line input line from user
     * @param myStorage storage file user has currently opened
     * @param toWrite indicator whether the command should be written into storage
     * @return string message to present to user
     */
    public String processTask (String line, StorageFile myStorage, boolean toWrite) {
        Task task = Parser.parseCommand(line, tasklist);
        String msg;
        if (task.getToRecord()){
            tasklist.add(task);
            msg = "Got it. I've added this task:\n"
                    + tasklist.get(tasklist.size() - 1).toString() + "\n"
                    + "Now you have " + tasklist.size() + " tasks in the list.";
            if (toWrite) {myStorage.append(task);}
        } else {
            msg = task.toString();
        }

        if(task.getToUpdateList()) {
            myStorage.write(tasklist);
        }

        return msg;
    }

}
