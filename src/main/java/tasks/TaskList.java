package tasks;
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

    // User Input
    public void addItem(String line) throws InvalidInputException{
        // Handling User Input 
        String[] input = line.trim().split(" ",2); 

        if(input[0].contains("deadline")){
            // deadline buy food by 5pm
            int by = input[1].indexOf("/by");
            String date = input[1].substring(by+4, input[1].length());
            String description = input[1].substring(0, by);
            task = new Deadline(description, date);
        }
        // event project meeting from Mon 2pm to 4pm
        else if(input[0].contains("event")){
            int from = input[1].indexOf("/from");
            int to = input[1].indexOf("/to");
            String fromDate = input[1].substring(from+6, to-1);
            String toDate = input[1].substring(to+3, input[1].length());
            String description = input[1].substring(0, from);
            task = new Event(description, fromDate, toDate);
        }
        else if(input[0].contains("todo")){
            task = new ToDo(input[1]);
        }
        else{
            throw new InvalidInputException();
        }

        tasks.add(task);
        totalTasks ++;
        System.out.println("---------------------------------------");
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void markItem(int item) throws MissingTaskException{
        task = tasks.get(item-1);
        if(item > totalTasks || item == 0){
            throw new MissingTaskException("Trying to delete a non existent task!");
        }
        if(task.isDone){
            System.out.println("Task already marked!");
            return;
        }
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    public void unmarkItem(int item) throws MissingTaskException {
        task = tasks.get(item-1);
        if(item > totalTasks || item == 0){
            throw new MissingTaskException("Trying to delete a non existent task!");
        }
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
        item = 0;

        if (totalTasks<1){
            throw new EmptyListException("Empty List");
        }
        if (item>totalTasks || item==0){
            throw new MissingTaskException("Trying to delete a non-existing task!");
        }

        tasks.remove((item-1));
        totalTasks --;
    }

}
