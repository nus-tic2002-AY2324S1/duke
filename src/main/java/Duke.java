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
                Task t = new Task(line);
                taskList[Task.getTotalTasks()] = t;
                System.out.println(line + " has been added to your list!");
            }

            System.out.println("---------------------------------------");


            System.out.println("Anything else?");

        }

    }
}
