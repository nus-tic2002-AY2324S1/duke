// MyList.java manages the list of tasks

package joshua;
import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<>();

    public TaskList(){

    }

    public Task getTask(int taskNum) {
        int taskIdx = taskNum - 1;
        return tasklist.get(taskIdx);
    }

    public int listSize() {
        return tasklist.size();
    }

    public void markTaskAsDone(int taskNum) {
        Task task = getTask(taskNum);
        task.setIsDone(true);
    }

    public void markTaskAsNotDone(int taskNum) {
        Task task = getTask(taskNum);
        task.setIsDone(false);
    }

    public void addToTaskList(Task task) {
        tasklist.add(task);
    }

    public void deleteFromTaskList(Task task) {
        tasklist.remove(task);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasklist.size(); i++) {
            int count = i + 1;
            str.append(count).append(". ").append(tasklist.get(i)).append("\n");
        }
        return str.toString();
    }
}
