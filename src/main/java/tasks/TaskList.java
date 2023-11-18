package tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import constants.ErrorMessages;
import exceptions.DukeException;
import exceptions.EmptyListException;
import exceptions.MissingTaskException;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int totalTasks = 0;

    public TaskList() {}

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
            throw new DukeException(ErrorMessages.ERROR_MARKING_MARKED_TASK);
        }
        task.setDone();
        return task;
    }

    public Task unmarkItem(int item) throws MissingTaskException, DukeException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException(ErrorMessages.TASK_NOT_FOUND);
        }
        Task task = tasks.get(item - 1);
        if (!task.isDone) {
            throw new DukeException(ErrorMessages.ERROR_UMARKING_UNMARKED_TASK);
        }
        task.unmarkTask();
        return task;
    }

    public ArrayList<Task> listItems() throws EmptyListException {
        if (totalTasks < 1) {
            throw new EmptyListException(ErrorMessages.EMPTY_LIST);
        }
        return this.tasks;
    }

    public Task deleteItem(int item) throws MissingTaskException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException(ErrorMessages.TASK_NOT_FOUND);
        }
        assert totalTasks > 0 : "task list size should be more than 0 to delete";

        Task task = tasks.get(item-1);
        tasks.remove((item - 1));
        totalTasks--;
        return task;
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
            else if (t instanceof tasks.Deadline) {
                tasks.Deadline d = (tasks.Deadline) t;
                LocalDate dueDate = d.getDueDate();
                if (dueDate.equals(due)) {
                    taskDueList.add(d);
                }
            }
            else if (t instanceof tasks.ToDoWithinPeriod) {
                tasks.ToDoWithinPeriod td = (tasks.ToDoWithinPeriod) t;
                LocalDate fromDate = td.getFromDate();
                LocalDate toDate = td.getToDate();
                if (fromDate.equals(due) || toDate.equals(due)) {
                    taskDueList.add(td);
                }
            }
        }
        return taskDueList;
    }
}
