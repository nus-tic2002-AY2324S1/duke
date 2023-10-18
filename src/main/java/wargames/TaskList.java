// MyList.java manages the list of tasks

package wargames;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<>();

    public TaskList(){

    }

    public Task getItem(int index){
        return tasklist.get(index);
    }

    public int listSize() {
        return tasklist.size();
    }

    public void markTaskAsDone(int taskNum){
        int taskIdx = taskNum - 1;
        try {
            Task task = getItem(taskIdx);
            task.setIsDone(true);
            Joshua.joshuaSays("Marked task: " + task);
        } catch (IndexOutOfBoundsException e) {
            Joshua.joshuaSays("Enter a number from the task list.");
        }
    }

    public void markTaskAsNotDone(int taskNum){
        int taskIdx = taskNum - 1;
        try {
            Task task = getItem(taskIdx);
            task.setIsDone(false);
            Joshua.joshuaSays("Unmarked task: " + task);
        } catch (IndexOutOfBoundsException e) {
            Joshua.joshuaSays("Enter a number from the task list.");
        }
    }

    public void addToTaskList(Task task){
        tasklist.add(task);
    }

    public void addToTaskList(Task[] arr){
        tasklist.addAll(Arrays.asList(arr));
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
