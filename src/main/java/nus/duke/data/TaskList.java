package nus.duke.data;

import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public SortedMap<Integer, Task> getTasks(LocalDate date) {
        SortedMap<Integer, Task> result = new TreeMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
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

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getLastTask() {
        return !tasks.isEmpty() ? tasks.get(tasks.size() - 1) : null;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
