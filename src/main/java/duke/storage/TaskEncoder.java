package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class TaskEncoder {
    public static ArrayList<String> encodeTask(TaskList tasks) {
        ArrayList<String> listOfResult = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            listOfResult.add(taskToString(t));
        }
        return listOfResult;
    }

    private static String taskToString(Task task) {
        StringBuilder taskStringBuilder = new StringBuilder();
        char abbreviation = task.getAbbreviation();
        taskStringBuilder.append(abbreviation);
        taskStringBuilder.append("|").append(task.convertTaskStatusToBinary());
        taskStringBuilder.append("|").append(task.getDukeDescription());
        switch(abbreviation){
        case 'D':
            Deadline deadline = (Deadline)task;
            taskStringBuilder.append("|").append(deadline.getByDateTime(Task.DATE_TIME_FORMAT_TOFILE));
            break;
        case 'E':
            Event event = (Event)task;
            taskStringBuilder.append("|").append(event.getFromDateTime(Task.DATE_TIME_FORMAT_TOFILE))
                    .append("|").append(event.getToDateTime(Task.DATE_TIME_FORMAT_TOFILE));
            break;
        default:
        }
        return taskStringBuilder.toString();
    }

}
