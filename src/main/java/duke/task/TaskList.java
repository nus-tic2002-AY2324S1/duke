package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Constructor
     */
    public TaskList(){}
    /**
     * The Tasklist Constructor
     * @param listOfTasks An ArrayList containing tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        tasks = listOfTasks;
    }
    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks in the TaskList.
     */
    public static int size(){
        return tasks.size();
    }
    /**
     * Retrieves the task at the specified index in the TaskList.
     * @param i The index of the task to retrieve.
     * @return The task at the specified index in the TaskList.
     */
    public Task get(int i){
        return tasks.get(i);
    }
    /**
     * Adds a task to the TaskList.
     * @param task The task to be added to the TaskList.
     */
    public void add(Task task){
        tasks.add(task);
    }
    /**
     * Retrieves the list of tasks from the TaskList.
     * @return An ArrayList containing tasks in the TaskList.
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
