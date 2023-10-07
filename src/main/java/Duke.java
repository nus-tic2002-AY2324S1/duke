import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        
        Scanner in = new Scanner(System.in);
        boolean typing = true;
        Task[] taskList = new Task[100];

        while(typing){
            try {
                String line = in.nextLine();

                if(line.equals("bye")){
                    System.out.println("Bye! Hope I'll get to see you soon! :)");
                    typing = false;
                    continue;
                }
                else if(line.equals("list")){
                    try{
                        listItems(taskList);
                    }
                    catch (EmptyListException e) {
                        System.out.println("Ooops! It looks like you don't have anything in your list!");
                        System.out.println("To call for help, type: help");
                    }
                }

                else if(line.contains("mark")){
                    try{
                        markItem(line, taskList);
                    }
                    catch(MissingTaskException e){
                        System.out.println("This task doesnt exist! Try again.");
                        System.out.println("To call for help, type: help");
                    }
                    
                }

                else if(line.contains("help")){
                    help();
                }

                else{
                    try{
                        addItem(line, taskList);
                    }
                    catch(InvalidInputException e){
                        System.out.println("Invalid Input.");
                        System.out.println("To call for help, type: help");
                    }
                    
                }

                System.out.println();
                System.out.println("What else can I do for you?");

            }

            catch (IndexOutOfBoundsException e){
                System.out.println("Hey, looks like you gave an incomplete sentence!");
                help();
            }



        }

    }

    private static void greet() {
        String botName = "Angel";
        System.out.println("Hello! I'm " + botName + " your personal friendly to-do list tracker bot.");
        System.out.println("I accept Deadlines, Events and simple To-Dos. Here's a mini manual!");
        System.out.println();
        help();
        System.out.println("What can I do for you?" + "\n");
    }

    public static void help(){
        System.out.println("To Add:");
        System.out.println("(a) A Deadline: start with 'deadline /by' and specify any day of the week. e.g.: deadline return book /by Sunday");
        System.out.println("(b) An Event: start with 'event' and a description, then '/from' and '/to' the Event Day and Time. e.g.: event project meeting /from Mon 2pm /to 4pm");
        System.out.println("(c) A To-Do: start with 'todo' and task name. e.g. todo borrow book");
        System.out.println("To List all items in your list, type: list"); 
        System.out.println("To Mark or Unmark your tasks, type: (a) mark 'task no.' or (b) unmark 'task no' ");
        System.out.println();
    }

    public static void addItem(String line, Task[] taskList) throws InvalidInputException{
        Task t;
        String[] input = line.trim().split(" ",2); 
        if(input[0].contains("deadline")){
            // deadline buy food /by 5pm
            int by = input[1].indexOf("/by");
            String date = input[1].substring(by+4, input[1].length());
            String description = input[1].substring(0, by);
            t = new Deadline(description, date);
        }
        // event project meeting /from Mon 2pm /to 4pm
        else if(input[0].contains("event")){
            int from = input[1].indexOf("/from");
            int to = input[1].indexOf("/to");
            String fromDate = input[1].substring(from+6, to-1);
            String toDate = input[1].substring(to+3, input[1].length());
            String description = input[1].substring(0, from);
            t = new Event(description, fromDate, toDate);
        }

        else if(!input[0].contains("todo")){
            throw new InvalidInputException();
        }
        else{
            // todo eat dinner
            t = new ToDo(input[1]);
        }

        taskList[Task.getTotalTasks()] = t;
        System.out.println("---------------------------------------");
        System.out.println("Got it. I've added this task: \n" + t);
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
    }

    private static void markItem(String line, Task[] taskList) throws MissingTaskException{
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

        if(itemNum > Task.getTotalTasks()){
            throw new MissingTaskException();
            
        }
        if(check.equals("unmark")){
            // Boolean completed = taskList[itemNum].isMarked();
            // completed ?  taskList[itemNum].unmarkTask() : "";
            if (!taskList[itemNum].isMarked()){
                System.out.println("Task was not previously marked!");
            }
            else{
                taskList[itemNum].unmarkTask();
                System.out.println("OK, I've marked this task as not done yet: " + taskList[itemNum]);
            }
        }
        else{
            if (taskList[itemNum].isMarked()){
                System.out.println("Task already marked!");
            }
            else{
                taskList[itemNum].markAsDone();
                System.out.println("Nice! I've marked this task as done: " + taskList[itemNum]);
            }
        }
    }

    private static void listItems(Task[] taskList) throws EmptyListException{

        if (Task.getTotalTasks()<1){
            throw new EmptyListException();
        }
        
        System.out.println("Here are the tasks in your list: ");
        for (int i=1; i<=Task.getTotalTasks(); i++){
            System.out.println(i + ". " + taskList[i]);
        }
    }
}
