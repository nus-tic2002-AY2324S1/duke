package duke.task;

import duke.exception.DukeException;

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
        if (index >= 0 && index < taskList.size()) {
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            return deletedTask;
        } else {
            throw new DukeException("Invalid index, Please enter a valid int number.");
        }
    }

    public Task markTaskAsDone(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Invalid task index. Please specify a valid task to mark as done.");
        }
        taskList.get(index).markAsDone();
        return taskList.get(index);
    }

    public Task unmarkTaskAsNotDone(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Invalid task index. Please specify a valid task to mark as done.");
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
