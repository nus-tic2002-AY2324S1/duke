import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
