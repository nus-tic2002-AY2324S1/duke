import java.util.ArrayList;
import java.util.List;

public class TaskList { 

    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }
    public int getListSize() {
        return list.size();
    }

    public void setList(List<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
        UI.printSeparator();
        System.out.println("Got it. I've added this task:");
        task.print();
        System.out.println("Now you have " + list.size() + " task(s) in the list");
        UI.printSeparator();
    }

    public void deleteTask(int index) {
        Task existing = list.get(index);
        list.remove(index);

        for (Task l : list) {
            System.out.println(l.description);
        }
        UI.printSeparator();
        System.out.println("Noted. I've removed this task:");
        existing.print();
        System.out.println("Now you have " + list.size() + " task(s) in the list");
        UI.printSeparator();
    }
}
