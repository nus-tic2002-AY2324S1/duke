package nus.duke.data;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.exceptions.InvalidCommandArgsDukeException;

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
     * Gets a sorted map of tasks that depend on a specific task number.
     *
     * @param taskNumber The task number to check for dependencies.
     * @return A sorted map of task indexes as keys and tasks that depend on the specified task number as values.
     */
    public SortedMap<Integer, AbstractTask> getAllTasksDependingOn(int taskNumber) {
        SortedMap<Integer, AbstractTask> result = new TreeMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            AbstractTask task = tasks.get(i);
            TaskAfterOption afterOption = task.getAfterOption();
            if (afterOption != null && afterOption.isAfterTask() && afterOption.getTaskNumber() == taskNumber) {
                result.put(i, task);
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
     * Marks a task as completed at the specified index, taking task dependencies into account.
     *
     * @param index The index of the task to be marked as completed.
     * @return The completed task.
     * @throws InvalidCommandArgsDukeException If a task dependency is not completed.
     */
    public AbstractTask markTask(int index) throws InvalidCommandArgsDukeException {
        assert index >= 0 && index < tasks.size();

        AbstractTask task = getTask(index);
        TaskAfterOption afterOption = task.getAfterOption();
        if (afterOption != null) {
            if (afterOption.isAfterTask()) {
                int dependencyTaskNumber = afterOption.getTaskNumber();
                int dependencyTaskIndex = dependencyTaskNumber - 1;
                assert dependencyTaskIndex >= 0 && dependencyTaskIndex < tasks.size();
                AbstractTask dependencyTask = getTask(dependencyTaskIndex);
                if (!dependencyTask.getDone()) {
                    throw new InvalidCommandArgsDukeException(
                        String.format("The dependent task #%d is not yet done.", dependencyTaskNumber));
                }
            } else if (afterOption.isAfterTime()) {
                LocalDateTime dependencyDateTime = afterOption.getDateTime();
                if (LocalDateTime.now().isBefore(dependencyDateTime)) {
                    throw new InvalidCommandArgsDukeException("The dependent datetime has not yet been reached.");
                }
            }
        }
        task.setDone(true);
        return task;
    }

    /**
     * Unmarks a task as not done at the specified index, considering task dependencies.
     *
     * @param index The index of the task to be marked as not done.
     * @return The task that has been marked as not done.
     * @throws InvalidCommandArgsDukeException If a task dependency is already marked as done.
     */
    public AbstractTask unmarkTask(int index) throws InvalidCommandArgsDukeException {
        int taskNumber = index + 1;
        SortedMap<Integer, AbstractTask> tasksDependingOn = getAllTasksDependingOn(taskNumber);
        for (Map.Entry<Integer, AbstractTask> entry : tasksDependingOn.entrySet()) {
            int taskIndexDependingOn = entry.getKey();
            AbstractTask task = entry.getValue();
            if (task.getDone()) {
                int taskNumberDependingOn = taskIndexDependingOn + 1;
                throw new InvalidCommandArgsDukeException(
                    String.format("The task #%d which depends on this task has been already done.",
                        taskNumberDependingOn));
            }
        }
        AbstractTask task = getTask(index);
        task.setDone(false);
        return task;
    }

    /**
     * Deletes a task at the specified index from the list.
     *
     * @param index The index of the task to remove.
     * @throws InvalidCommandArgsDukeException if the provided index is out of range.
     */
    public void deleteTask(int index) throws InvalidCommandArgsDukeException {
        assert index >= 0 && index < tasks.size();

        onDeleteTask(index);
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
     * Handles the adjustments needed when a task is to be deleted from the list.
     *
     * @param taskIndex The index of the task to be removed.
     * @throws InvalidCommandArgsDukeException if a task depends on the task to be removed.
     */
    private void onDeleteTask(int taskIndex) throws InvalidCommandArgsDukeException {
        int deletingTaskNumber = taskIndex + 1;
        for (int i = taskIndex + 1; i < tasks.size(); i++) {
            AbstractTask task = getTask(i);
            TaskAfterOption taskAfterOption = task.getAfterOption();
            if (taskAfterOption != null && taskAfterOption.isAfterTask()) {
                if (taskAfterOption.getTaskNumber() == taskIndex + 1) {
                    throw new InvalidCommandArgsDukeException(
                        MessageFormat.format(
                            "The task #{0} depends on the task #{1}, please delete the task #{0} first.",
                            i + 1,
                            deletingTaskNumber));
                }
                if (taskAfterOption.getTaskNumber() > taskIndex + 1) {
                    task.setAfterOption(new TaskAfterOption(taskAfterOption.getTaskNumber() - 1));
                }
            }
        }
    }
}
