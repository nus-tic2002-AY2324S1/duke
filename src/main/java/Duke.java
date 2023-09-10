import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String botName = "Angel";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?" + "\n");
        
        Scanner in = new Scanner(System.in);
        boolean typing = true;
        Task[] taskList = new Task[100];

        while(typing){
            String line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("Bye! Hope I'll get to see you soon! :)");
                typing = false;
                continue;
            }

            else if(line.equals("list")){
                System.out.println("Here are the tasks in your list: ");
                for (int i=1; i<=Task.getTotalTasks(); i++){
                    System.out.println(i + ". " + taskList[i]);
                }
            }

            else if(line.contains("mark")){
                int dividerPosition = line.indexOf(" ");
                String check = line.substring(0, dividerPosition);
                int itemNum = Integer.parseInt(line.substring(dividerPosition+1,dividerPosition+2));

                if(itemNum > Task.getTotalTasks()){
                    System.out.println("This task doesn't exist! What else can I do for you? ");
                    continue;
                }
                if(check.equals("unmark")){
                    taskList[itemNum].unmarkTask();
                    System.out.println("OK, I've marked this task as not done yet: " + taskList[itemNum]);
                }
                else{
                    taskList[itemNum].markAsDone();
                    System.out.println("Nice! I've marked this task as done: " + taskList[itemNum]);
                }
                
            }

            else{
                Task t;
                String[] input = line.split(" ",2); 
                if(input[0].contains("deadline")){
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

                // else if(input[0].contains("todo"))
                else{
                    t = new ToDo(input[1]);
                }

                taskList[Task.getTotalTasks()] = t;
                System.out.println("---------------------------------------");
                System.out.println("Got it. I've added this task: \n" + t);
                System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
            }

            System.out.println("---------------------------------------");
            System.out.println("Anything else?");

        }

    }
}
