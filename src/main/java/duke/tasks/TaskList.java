package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import duke.Duke;
import duke.exceptions.EmptyListException;
import duke.exceptions.MissingTaskException;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int totalTasks;
    protected Task task;

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
        this.task = new Deadline(description, date);
        tasks.add(task);
        this.totalTasks++;
        return this.task;
    }

    public Task addToDo(String description) {
        this.task = new ToDo(description);
        tasks.add(task);
        this.totalTasks++;
        return this.task;
    }

    public Task addEvent(String description, LocalDate fromDate, LocalDate toDate) {
        this.task = new Event(description, fromDate, toDate);
        this.tasks.add(this.task);
        this.totalTasks++;
        return this.task;
    }

    public Task markItem(int item) throws MissingTaskException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException("Trying to mark a non existent task!");
        } else {
            task = tasks.get(item - 1);
            if (task.isDone) {
                System.out.println("Task already marked!");
            } else {
                task.setDone();
            }
            return task;
        }
    }

    public void unmarkItem(int item) throws MissingTaskException {
        if (item > totalTasks || item == 0) {
            throw new MissingTaskException("Trying to unmark a non existent task!");
        } else {
            task = tasks.get(item - 1);
            if (!task.isDone) {
                System.out.println("Task was not previously marked!");
                return;
            }
            task.unmarkTask();
            System.out.println("OK, I've marked this task as not done yet: " + task);

        }
    }

    public void listItems() throws EmptyListException {
        if (totalTasks < 1) {
            throw new EmptyListException("Empty List");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 1; i <= totalTasks; i++) {
                System.out.println(i + ". " + tasks.get(i - 1));
            }
        }

    }

    public void deleteItem(int item) throws EmptyListException, MissingTaskException {

        if (totalTasks < 1) {
            throw new EmptyListException("Empty List");
        } else if (item > totalTasks || item == 0) {
            throw new MissingTaskException("Trying to delete a non-existing task!");
        } else {
            System.out.println("Removing task " + item + ":" + tasks.get(item - 1));
            tasks.remove((item - 1));
            totalTasks--;
        }

    }

    public Task setPriority(int item, Priority p) throws EmptyListException, MissingTaskException {

        if (totalTasks < 1) {
            throw new EmptyListException("Empty List");
        } else if (item > totalTasks || item == 0) {
            throw new MissingTaskException("Trying to add Priority to a non-existing task!");
        } else {
            Task t = tasks.get(item - 1);
            t.setTaskPriority(p);
            return t;
        }

    }

    public ArrayList<Task> getTasksDue(LocalDate due) {
        // iterate through arraylist to check for Due Dates from Events and Deadlines

        ArrayList<Task> taskDueList = new ArrayList<Task>();

        for (Task t : this.tasks) {
            if (t instanceof duke.tasks.Event) {
                duke.tasks.Event e = (duke.tasks.Event) t;
                LocalDate taskDue = e.getDueDate();
                if (taskDue.equals(due)) {
                    taskDueList.add(e);
                }
            } else if (t instanceof duke.tasks.Deadline) {
                duke.tasks.Deadline d = (duke.tasks.Deadline) t;
                LocalDate taskDue = d.getDueDate();
                if (taskDue.equals(due)) {
                    taskDueList.add(d);
                }
            } else {
                continue;
            }
        }
        return taskDueList;
    }
}
