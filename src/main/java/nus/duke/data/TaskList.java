package nus.duke.data;

import nus.duke.data.tasks.AbstractTask;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The `TaskList` class represents a list of tasks in Duke.
 */
public class TaskList implements Iterable<AbstractTask> {
    private final List<AbstractTask> tasks;

    /**
     * Instantiates a new `TaskList` with an empty list of tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Instantiates a new `TaskList` with the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the `TaskList`.
     */
    public TaskList(List<AbstractTask> tasks) {
        assert tasks != null;

        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(AbstractTask task) {
        assert task != null;

        tasks.add(task);
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return A list of all tasks in the task list.
     */
    public List<AbstractTask> getAllTasks() {
        return tasks;
    }

    /**
     * Gets tasks scheduled for a specified date.
     *
     * @param date The date for which tasks are to be retrieved.
     * @return A sorted map of task indices and corresponding tasks scheduled for the specified date.
     */
    public SortedMap<Integer, AbstractTask> getTasks(LocalDate date) {
        assert date != null;

        SortedMap<Integer, AbstractTask> result = new TreeMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            AbstractTask task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    result.put(i, task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().minusDays(1).isBefore(date)
                        && event.getTo().toLocalDate().plusDays(1).isAfter(date)) {
                    result.put(i, task);
                }
            }
        }
        return result;
    }

    /**
     * Retrieves a task at a specified index in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public AbstractTask getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the last task in the list.
     *
     * @return The last task in the task list, or `null` if the list is empty.
     */
    public AbstractTask getLastTask() {
        return !tasks.isEmpty() ? tasks.get(tasks.size() - 1) : null;
    }

    /**
     * Removes a task at the specified index from the list.
     *
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {
        assert index >= 0 && index < tasks.size();

        onRemoveTask(index);
        tasks.remove(index);
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public Iterator<AbstractTask> iterator() {
        return tasks.iterator();
    }

    /**
     * Handles the adjustments needed when a task is removed from the list.
     *
     * @param taskIndex The index of the removed task.
     */
    private void onRemoveTask(int taskIndex) {
        for (int i = taskIndex + 1; i < tasks.size(); i++) {
            AbstractTask task = getTask(i);
            TaskAfterOption taskAfterOption = task.getAfterOption();
            if (taskAfterOption != null
                    && taskAfterOption.isAfterTask()
                    && taskAfterOption.getTaskNumber() > taskIndex + 1) {
                task.setAfterOption(new TaskAfterOption(taskAfterOption.getTaskNumber() - 1));
            }
        }
    }
}
