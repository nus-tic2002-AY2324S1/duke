import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
            // Rename the chatbot
            // Greet the user

        System.out.println ("*************************************************************");
        String logo = "  ____   ____  ||        ____   ____              __     _____  \n"
                    + " |    | |    | ||       |    | |    | |    |     //\\\\      |     \n"
                    + " |____| |    | ||_____  |    | |      |____|    //__\\\\     |        \n"
                    + " | \\    |    | ||    || |    | |      |    |   //    \\\\    |    \n"
                    + " |   \\  |____| ||____|| |____| |____| |    |  //      \\\\   |         \n";
        System.out.println("Hello !!!!!!!!!!!!!!!!!  from\n" + logo);
        System.out.println("What can I do for you? :) ");
        System.out.println ("*************************************************************");

        // Create a Scanner object for user input
        Scanner userInput = new Scanner(System.in);
        String userCommand;

        //Declaring an array to store the commands;
        ArrayList<Task> list = new ArrayList<Task>();


            do {
                System.out.print("Enter your command: ");
                userCommand = userInput.nextLine();


                if (userCommand.startsWith("mark")) {
                    try {
                        if (userCommand.substring(4).isEmpty()) {
                            throw new DukeException("Invalid input. Please provide a valid task number.");
                        }

                        int taskNo = Integer.parseInt(userCommand.substring(5));
                        Task t = list.get(taskNo - 1);
                        t.setTaskDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t.getStatusIcon() + " " + t.getDescription());

                    }catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");

                } else if (userCommand.startsWith("unmark")) {
                    try {
                        if (userCommand.substring(6).isEmpty()) {
                            throw new DukeException("Invalid input. Please provide a valid task number.");
                        }

                        int taskNo = Integer.parseInt(userCommand.substring(7));
                        Task t = list.get(taskNo-1);
                        t.setTaskNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t.getStatusIcon() + " " + t.getDescription());

                    }catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");

                } else if (userCommand.startsWith("todo")) {
                    String description = userCommand.substring(4);

                    try {
                        if (description.isEmpty()) {
                            throw new DukeException("The description of a Todo cannot be empty.");
                        }

                        Todo t = new Todo(description);
                        list.add(t);
                        System.out.println("Got it. I've added this task:\n  " + t);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                    } catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");

                } else if (userCommand.startsWith("deadline")) {
                    // Parse the description and deadline details here
                    String[] parts = userCommand.substring(8).split(" /by ");//reused from chatGpt
                    String description = parts[0];

                    try {
                        if (description.isEmpty()) {
                            throw new DukeException("The description of a Deadline cannot be empty.");
                        }

                        String by = parts[1];
                        Deadline d = new Deadline(description, by);
                        list.add(d);
                        System.out.println("Got it. I've added this task:\n  " + d);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                    } catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");

                } else if (userCommand.startsWith("event")) {
                    // Parse the description and event details here
                    String[] parts = userCommand.substring(5).split(" /from "); //reused from chatGpt
                    String description = parts[0];

                    try {
                        if (description.isEmpty()) {
                            throw new DukeException("The description of an Event cannot be empty.");
                        }

                        String[] time = parts[1].split(" /to ");
                        if (time.length == 2) {
                            String start = time[0];
                            String end = time[1];
                            Event e = new Event(description, start, end);
                            list.add(e);
                            System.out.println("Got it. I've added this task:\n  " + e);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        } else {
                            System.out.println("Invalid event format.");
                        }

                    } catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");

                    // list all added items if user command is "list"
                } else if (userCommand.startsWith("delete")){
                    try {
                        if (userCommand.substring(6).isEmpty()) {
                            throw new DukeException("Invalid input. Please provide a valid task number.");
                        }
                        int taskNo = Integer.parseInt(userCommand.substring(7));
                        Task t = list.get(taskNo-1);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("  " + t );
                        list.remove(t);

                        if (list.size()<=1) {
                            System.out.println("Now you have " + list.size() + " task in the list.");
                        }
                        else {
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        }

                    }catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");

                }else if (userCommand.startsWith("list")) {
                    try{
                        if(list.isEmpty()) {
                            throw new DukeException("OOPS!!!...There are no tasks in your list :-(");
                        }

                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i + 1) + ". " + t.toString());

                    }
                    }catch(DukeException d) {
                        System.out.println( d.getMessage());
                    }

                    System.out.println("--------------------------------------------------------");

                } else {
                    try {
                        if (!userCommand.equalsIgnoreCase("bye")) {
                            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :");
                        }
                    } catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    System.out.println("--------------------------------------------------------");
                }

            }while (!userCommand.equalsIgnoreCase("bye")) ;

                // Exit
                System.out.println("Goodbye :(  from \n \n" + logo );
                System.out.println("Hope to see you again soon!");
                System.out.println("*************************************************************");
    }
}

