import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> list = new ArrayList<String>();

            do {
                System.out.print("Enter your command: ");
                userCommand  = userInput.nextLine();

                //Exit if the user command is "bye"
                if (userCommand.equalsIgnoreCase("bye")){
                    break;
                }

                // list all added items if user command is "list"
                int x=1;
                if(userCommand.equalsIgnoreCase("list")){
                    System.out.println ("--------------------------------------------------------");
                    for (String i : list) {
                        System.out.println( x + ". " + i);
                        x++;
                    }
                    System.out.println ("--------------------------------------------------------");
                    continue;
                }

                System.out.println("added: " + userCommand);
                System.out.println ("--------------------------------------------------------");
                list.add(userCommand);

            }while(!userCommand.equalsIgnoreCase("bye"));

        // Exit
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println ("--------------------------------------------------------");
    }
}

