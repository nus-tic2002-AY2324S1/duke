package duke.task;

import duke.exception.DukeException;
import duke.exception.IndexOutOfRangeException;
import duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList.getTaskList();
    }


    public Task deleteTask(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfRangeException("Index out of range, Please enter a valid int number within the size of list.");
        } else {
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            return deletedTask;
        }
    }

    public Task markTaskAsDone(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfRangeException("Index out of range, Please enter a valid int number within the size of list.");
        }
        taskList.get(index).markAsDone();
        return taskList.get(index);
    }

    public Task unmarkTaskAsNotDone(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfRangeException("Index out of range, Please enter a valid int number within the size of list.");
        }
        taskList.get(index).unmarkDone();
        Task task = taskList.get(index);
        return task;
    }

    public TaskList copyTaskList() {
        ArrayList<Task> copiedTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            copiedTasks.add(task.makeCopy());
        }
        return new TaskList(copiedTasks);
    }


    public TaskList findTasksByKeyword(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

}
