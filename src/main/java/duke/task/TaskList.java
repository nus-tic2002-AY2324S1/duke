package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int size = 0;
    public TaskList(){}
    public TaskList(int tasks) {

    }

    public int size(){
        return size;
    }

    public Task get(int i){
        return tasks.get(i);
    }

    public void add(Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
