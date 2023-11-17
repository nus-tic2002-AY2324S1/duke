package tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import constants.ErrorMessages;
import exceptions.DukeException;
import exceptions.EmptyListException;
import exceptions.MissingTaskException;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int totalTasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.totalTasks = 0;
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
        this.totalTasks = taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public Integer getTotalTasks() {
        return this.totalTasks;
    }

    public Task addDeadline(String description, LocalDate date) {
        Task task = new Deadline(description, date);
        tasks.add(task);
        this.totalTasks++;
        return task;
    }

    public Task addToDo(String description) {
        Task task = new ToDo(description);
        tasks.add(task);
        this.totalTasks++;
        return task;
    }

    public Task addToDoWithinPeriod(String description, LocalDate start, LocalDate end) {
        Task task = new ToDoWithinPeriod(description, start, end);
        tasks.add(task);
        this.totalTasks++;
        return task;
    }

    public Task addEvent(String description, LocalDate fromDate, LocalDate toDate) {
        Task task = new Event(description, fromDate, toDate);
        tasks.add(task);
        this.totalTasks++;
        return task;
    }

    public Task markItem(int item) throws MissingTaskException, DukeException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException(ErrorMessages.TASK_NOT_FOUND);
        }
        Task task = tasks.get(item - 1);
        if (task.isDone) {
            System.out.println(
                    "Whoa there, adventurer! Attempting to mark an already marked task? Double the glory, I suppose!");
        } else {
            task.setDone();
        }
        return task;
    }

    public void unmarkItem(int item) throws MissingTaskException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException(ErrorMessages.TASK_NOT_FOUND);
        }

        Task task = tasks.get(item - 1);
        if (!task.isDone) {
            System.out.println(
                    "Hold your horses! Attempting to unmark a task that was never in the victory parade?");
            return;
        }
        task.unmarkTask();
        System.out.println("Alrighty then! I've given this task a rain check: " + task);

    }

    public void listItems() throws EmptyListException {
        if (totalTasks < 1) {
            throw new EmptyListException(ErrorMessages.EMPTY_LIST);
        }

        System.out.println("Ta-da! Behold, the magnificent lineup of tasks in your grand list:");
        for (int i = 1; i <= totalTasks; i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void deleteItem(int item) throws MissingTaskException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException(ErrorMessages.TASK_NOT_FOUND);
        }
        assert totalTasks > 0 : "task list size should be more than 0 to delete";

        System.out.println("TaskMaster at the helm! 🪄 Time to perform a task vanishing act on: \n" + item + ":"
                + tasks.get(item - 1));
        tasks.remove((item - 1));
        totalTasks--;
    }

    public Task setPriority(int item, Priority p) throws EmptyListException, MissingTaskException {

        if (totalTasks < 1) {
            throw new EmptyListException(ErrorMessages.EMPTY_LIST);
        }
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException(ErrorMessages.TASK_NOT_FOUND);
        }
        Task t = tasks.get(item - 1);
        t.setTaskPriority(p);
        return t;

    }

    public ArrayList<Task> getTasksDue(LocalDate due) {
        // iterate through arraylist to check for Due Dates from Events and Deadlines

        ArrayList<Task> taskDueList = new ArrayList<Task>();

        for (Task t : this.tasks) {
            if (t instanceof tasks.Event) {
                tasks.Event e = (tasks.Event) t;
                LocalDate starDate = e.getStartDate();
                LocalDate endDate = e.getEndDate();
                if (starDate.equals(due) || endDate.equals(due)) {
                    taskDueList.add(e);
                }
            }
            if (t instanceof tasks.Deadline) {
                tasks.Deadline d = (tasks.Deadline) t;
                LocalDate taskDue = d.getDueDate();
                if (taskDue.equals(due)) {
                    taskDueList.add(d);
                }
            }
        }
        return taskDueList;
    }
}
