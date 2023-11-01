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


                } else if (userCommand.startsWith("todo ")) {
                    String description = userCommand.substring(5);
                    Todo t = new Todo(description);
                    list.add(t);
                    System.out.println("Got it. I've added this task:\n  " + t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println ("--------------------------------------------------------");

                }   else if (userCommand.startsWith("deadline ")) {
                        // Parse the description and deadline details here
                        String[] parts = userCommand.substring(9).split(" /by ");
                        if (parts.length == 2) {
                            String description = parts[0];
                            String by = parts[1];
                            Deadline d = new Deadline(description, by);
                            list.add(d);
                            System.out.println("Got it. I've added this task:\n  " + d);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            System.out.println ("--------------------------------------------------------");
                        } else {
                            System.out.println("Invalid deadline format.");
                        }
                    }

                    else if (userCommand.startsWith("event ")) {
                        // Parse the description and event details here
                        String[] parts = userCommand.substring(6).split(" /from ");
                        if (parts.length == 2) {
                            String description = parts[0];
                            String[] time = parts[1].split(" /to ");
                            if (time.length == 2) {
                                String start = time[0];
                                String end = time[1];
                                Event e = new Event(description, start, end);
                                list.add(e);
                                System.out.println("Got it. I've added this task:\n  " + e);
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
                                System.out.println ("--------------------------------------------------------");
                            } else {
                                System.out.println("Invalid event format.");
                            }
                        } else {
                            System.out.println("Invalid event format.");
                        }

                    // list all added items if user command is "list"
                } else if(userCommand.equalsIgnoreCase("list")){
                    System.out.println ("--------------------------------------------------------");
                    System.out.println ("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i + 1) +". " + t.toString() );
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

