package data;

import commandsTask.*;

import java.util.List;
import java.util.ArrayList;
import exception.*;
import parser.Parser;
import storage.StorageFile;

public class TaskList {
    private final List<Task> tasklist = new ArrayList<>();

    public TaskList () {}

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
