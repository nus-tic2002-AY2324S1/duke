package tasks;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList{
    public ArrayList<Task> tasks;
    public int totalTasks;
    public Task task;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
        this.totalTasks = 0;
    }

    public TaskList(ArrayList<Task> taskList){
        this.tasks = taskList;
        this.totalTasks = taskList.size();
    }

    public Task addDeadline(String description, LocalDate date){
        task = new Deadline(description, date);
        tasks.add(task);
        totalTasks ++;
        return task;
    }

    public Task addToDo(String description){
        task = new ToDo(description);
        tasks.add(task);
        totalTasks ++;
        return task;
    }

    public Task addEvent(String description, LocalDate fromDate, LocalDate toDate){
        task = new Event(description, fromDate, toDate);
        tasks.add(task);
        totalTasks ++;
        return task;
    }

    public void markItem(int item) throws MissingTaskException{
        if(item > totalTasks || item == 0){
            throw new MissingTaskException("Trying to mark a non existent task!");
        }
        task = tasks.get(item-1);
        if(task.isDone){
            System.out.println("Task already marked!");
            return;
        }
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    public void unmarkItem(int item) throws MissingTaskException {
        if(item > totalTasks || item == 0){
            throw new MissingTaskException("Trying to unmark a non existent task!");
        }
        task = tasks.get(item-1);
        if(!task.isDone){
            System.out.println("Task was not previously marked!");
            return;
        }
        task.unmarkTask();
        System.out.println("OK, I've marked this task as not done yet: " + task);
    }

    public void listItems() throws EmptyListException{
        if (totalTasks<1){
            throw new EmptyListException("Empty List");
        }   
        System.out.println("Here are the tasks in your list: ");
        for (int i=1; i<=totalTasks; i++){
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

    public void deleteItem(int item) throws EmptyListException, MissingTaskException{

        if (totalTasks<1){
            throw new EmptyListException("Empty List");
        }
        if (item>totalTasks || item==0){
            throw new MissingTaskException("Trying to delete a non-existing task!");
        }
        System.out.println("Removing task " + item + ":" + tasks.get(item-1));
        tasks.remove((item-1));
        totalTasks --;
    }

}
