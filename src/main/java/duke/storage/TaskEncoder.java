package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class TaskEncoder {
    /**
     * Encodes a TaskList into a list of strings.
     *
     * @param tasks The TaskList to be encoded.
     * @return An ArrayList containing string representations of tasks from the input TaskList.
     */
    public static ArrayList<String> encodeTaskListToStringList(TaskList tasks) {
        ArrayList<String> listOfResult = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            listOfResult.add(convertTaskToString(t));
        }
        return listOfResult;
    }

    /**
     * Converts a Task object into a string representation with specific delimiters.
     *
     * @param task The Task object to be converted into a string.
     * @return A string representation of the input Task object.
     */
    private static String convertTaskToString(Task task) {
        StringBuilder taskStringBuilder = new StringBuilder();
        char abbreviation = task.getAbbreviation();
        taskStringBuilder.append(abbreviation);
        taskStringBuilder.append("|").append(task.convertTaskStatusToBinary());
        taskStringBuilder.append("|").append(task.getDukeDescription());
        switch (abbreviation) {
        case 'D':
            Deadline deadline = (Deadline) task;
            taskStringBuilder.append("|").append(deadline.getByDateTime(Task.DATE_TIME_FORMAT_TOFILE));
            break;
        case 'E':
            Event event = (Event) task;
            taskStringBuilder.append("|").append(event.getFromDateTime(Task.DATE_TIME_FORMAT_TOFILE))
                    .append("|").append(event.getToDateTime(Task.DATE_TIME_FORMAT_TOFILE));
            break;
        default:
        }
        return taskStringBuilder.toString();
    }

}
