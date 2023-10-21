package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(){}
    public TaskList(ArrayList<Task> listOfTasks) {
        tasks = listOfTasks;
    }

    public static int size(){
        return tasks.size();
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
