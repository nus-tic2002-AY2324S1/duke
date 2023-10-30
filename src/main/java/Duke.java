import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;



public class Duke {
    public static void main(String[] args) {
        // Rename the chatbot
        String chatbotName = "RoboChat";

        // Greet the user
        System.out.println ("--------------------------------------------------------");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println ("--------------------------------------------------------");


        // Create a Scanner object for user input
        Scanner userInput = new Scanner(System.in);
        String userCommand;

        //Declaring an array to store the commands;
        ArrayList<Task> list = new ArrayList<Task>();

            do {
                System.out.print("Enter your command: ");
                userCommand  = userInput.nextLine();

                //Exit if the user command is "bye"
                if (userCommand.equalsIgnoreCase("bye")){
                    break;
                }

                if (userCommand.startsWith("mark ")) {
                    int taskNo = Integer.parseInt(userCommand.substring(5));
                    Task t = list.get(taskNo-1);
                    t.setTaskDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.getStatusIcon() + " " + t.getDescription());


                } else if (userCommand.startsWith("unmark ")) {
                    int taskNo = Integer.parseInt(userCommand.substring(7));
                    Task t = list.get(taskNo-1);
                    t.setTaskNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t.getStatusIcon() + " " + t.getDescription());

                // list all added items if user command is "list"
                } else if(userCommand.equalsIgnoreCase("list")){
                    System.out.println ("--------------------------------------------------------");
                    System.out.println ("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i + 1) + " " + t.getStatusIcon() + " " + t.getDescription());
                    }
                    System.out.println ("--------------------------------------------------------");
                    continue;

                } else {
                    Task newTask = new Task(userCommand);
                    list.add(newTask);
                    System.out.println("added: " + newTask.getDescription());

                }

            }while(!userCommand.equalsIgnoreCase("bye"));

        // Exit
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println ("--------------------------------------------------------");
    }
}

