package storage;

import data.TaskList;
import data.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskListEncoder {
    public static Collection<String> encodeTaskList(TaskList taskList) {
        ArrayList<String> encodedTaskList = new ArrayList<>();
        List<Task> tasks = taskList.getAllTasks();
        for (Task task : tasks) {
            encodedTaskList.add(encodeTaskToString(task));
        }
        return encodedTaskList;
    }

    private static String encodeTaskToString(Task task) {
        return task.encode();
    }
}
