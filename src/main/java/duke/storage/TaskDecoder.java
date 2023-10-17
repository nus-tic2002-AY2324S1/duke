package duke.storage;

import duke.command.Command;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskDecoder {
    public static ArrayList<Task> decodeTask(List<String> strings) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String string: strings) {
            tasks.add(decodeTaskFromString(string));
        }
        return tasks;
    }

    private static Task decodeTaskFromString(String string) {
        char abbreviation = string.charAt(0);
        Command command;
        switch(abbreviation){
        case 'T':

        case 'D':
        case 'E':
        default:
        }
        return new Todo();
    }
}
