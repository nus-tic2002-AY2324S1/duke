package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList class is responsible for managing a list of tasks.
 * It provides methods for adding, removing, and retrieving tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    /**
     * Constructs a new TaskList object with the given list of tasks.
     *
     * @param listOfTasks An ArrayList containing tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        tasks = listOfTasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index in the TaskList.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index in the TaskList.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the list of tasks from the TaskList.
     *
     * @return An ArrayList containing tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves tasks from the TaskList where the specified date matches the task's date.
     *
     * @param date The date to match tasks against.
     * @return ArrayList of tasks with the specified date.
     */
    public ArrayList<Task> getTasksByDate(LocalDateTime date) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (isDateFound(date, task)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns a list of tasks that contain the provided search string in their description.
     *
     * @param search The search string to be matched against task descriptions.
     * @return ArrayList of tasks matching the search string.
     */
    public ArrayList<Task> getTaskBySearchString(String search) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (isSearchFound(search, task)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Checks if the provided search string is found within the task's description.
     *
     * @param search The search string to be matched against the task's description.
     * @param task   The task to be checked.
     * @return True if the search string is found in the task's description, false otherwise.
     */
    private boolean isSearchFound(String search, Task task) {
        String description = task.getDukeDescription();
        return description.contains(search);
    }

    /**
     * Checks if the specified date matches the date of the given task.
     *
     * @param date The date to match against the task's date.
     * @param task The task to check for a matching date.
     * @return True if the specified date matches the task's date, false otherwise.
     */
    private boolean isDateFound(LocalDateTime date, Task task) {
        boolean isDeadline = task instanceof Deadline;
        boolean isEvent = task instanceof Event;
        if (!isDeadline && !isEvent) {
            return false;
        }
        if (isDeadline) {
            Deadline deadline = (Deadline) task;
            return areDatesEqual(date, deadline.getByDateTime());
        } else {
            Event event = (Event) task;
            boolean isEqualFromDate = areDatesEqual(date, event.getFromDateTime());
            boolean isEqualToDate = areDatesEqual(date, event.getToDateTime());
            return isEqualFromDate || isEqualToDate;
        }
    }

    /**
     * Compares two LocalDateTime objects to check if they have the same year, month, and day.
     *
     * @param d1 The first LocalDateTime object to be compared.
     * @param d2 The second LocalDateTime object to be compared.
     * @return true if the year, month, and day are equal; false otherwise.
     */
    public boolean areDatesEqual(LocalDateTime d1, LocalDateTime d2) {
        if (d1.getYear() != d2.getYear()) {
            return false;
        } else if (d1.getMonth() != d2.getMonth()) {
            return false;
        } else {
            return d1.getDayOfMonth() == d2.getDayOfMonth();
        }
    }

    /**
     * Removes the task at the specified index from the list of tasks.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    public char getAbbreviation(int index){
        for (int i = 0; i < tasks.size(); i++) {
             if(i == index-1){
                return tasks.get(i).getAbbreviation();
             }
        }
        return 'X';
    }
}
