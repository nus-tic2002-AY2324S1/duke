import java.util.ArrayList;

public class taskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected int totalTasks = 0;
    protected Task task;

    protected void addItem(String line) throws InvalidInputException{
        String[] input = line.trim().split(" ",2); 
        if(input[0].contains("deadline")){
            // deadline buy food /by 5pm
            int by = input[1].indexOf("/by");
            String date = input[1].substring(by+4, input[1].length());
            String description = input[1].substring(0, by);
            task = new Deadline(description, date);
        }
        // event project meeting /from Mon 2pm /to 4pm
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
            //throw new InvalidInputException();
        }
        else{
            // todo eat dinner
            throw new InvalidInputException();
        }

        tasks.add(task);
        totalTasks ++;
        System.out.println("---------------------------------------");
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    protected void markItem(String line) throws MissingTaskException{
        int dividerPosition = line.indexOf(" ");
        String check = line.substring(0, dividerPosition);
        int itemNum = 0;

        try{
            itemNum = Integer.parseInt(line.substring(dividerPosition+1,dividerPosition+2));
        }
        catch (NumberFormatException e) {
            System.out.println("Please provide the task number as an Integer!");
            return;
        }

        if(itemNum > totalTasks || itemNum == 0){
            throw new MissingTaskException();
            
        }
        task = tasks.get(itemNum-1);
        if(check.equals("unmark")){
            if (!task.isMarked()){
                System.out.println("Task was not previously marked!");
            }
            else{
                task.unmarkTask();
                System.out.println("OK, I've marked this task as not done yet: " + task);
            }
        }
        else{
            if (task.isMarked()){
                System.out.println("Task already marked!");
            }
            else{
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: " + task);
            }
        }
    }

    protected void listItems() throws EmptyListException{
        if (totalTasks<1){
            throw new EmptyListException();
        }   
        System.out.println("Here are the tasks in your list: ");
        for (int i=1; i<=totalTasks; i++){
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

    protected void deleteItem(String line) throws EmptyListException, MissingTaskException{
        int item = 0;

        if (totalTasks<1){
            throw new EmptyListException();
        }

        try{
            item = Integer.parseInt(line.split(" ")[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Please provide the task number as an Integer!");
            return;
        }

        if (item>totalTasks || item==0){
            throw new MissingTaskException();
        }

        System.out.println("Removing task " + item + ":" + tasks.get(item-1));
        tasks.remove((item-1));
        totalTasks --;
        System.out.println("That's one less thing to do! You now have " + totalTasks + " tasks left.");
    }
}
