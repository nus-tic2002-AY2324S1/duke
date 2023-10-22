package nus.duke.storage;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The `TaskListEncoder` class is responsible for encoding a `TaskList` into a collection of string representations
 * of tasks. Each task is converted to a string and added to the collection for storage.
 */
public class TaskListEncoder {
    /**
     * Encodes a `TaskList` into a collection of string representations of tasks.
     *
     * @param taskList The `TaskList` to be encoded.
     * @return A collection of encoded task representations as strings.
     */
    public static Collection<String> encodeTaskList(TaskList taskList) {
        ArrayList<String> encodedTaskList = new ArrayList<>();
        List<AbstractTask> tasks = taskList.getAllTasks();
        for (AbstractTask task : tasks) {
            encodedTaskList.add(encodeTaskToString(task));
        }
        return encodedTaskList;
    }

    private static String encodeTaskToString(AbstractTask task) {
        return task.encode();
    }
}
