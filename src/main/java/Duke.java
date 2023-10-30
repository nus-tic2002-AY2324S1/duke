import java.util.Scanner;

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
            do {
                System.out.print("Enter your command: ");
                userCommand  = userInput.nextLine();

                if (userCommand.equalsIgnoreCase("bye")){
                    break;
                }
                System.out.println("The command entered by the user is: " + userCommand);
                System.out.println ("--------------------------------------------------------");

            }while(!userCommand.equalsIgnoreCase("bye"));

        // Exit
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println ("--------------------------------------------------------");
    }
}

