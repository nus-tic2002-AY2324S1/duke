// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        Scanner scanner = new Scanner(System.in);
        String [] tasks = new String[100];
        int taskCounter = 0;

        // Greet the user
        String logo = "    ____                       __\n" +
                "   / __ \\__  ______  ___  ____/ /\n" +
                "  / / / / / / / __ \\/ _ \\/ __  /\n" +
                " / /_/ / /_/ / /_/ /  __/ /_/ /\n" +
                "/_____/\\__,_/ .___/\\___/\\__,_/\n" +
                "           /_/";
        System.out.println("Hello! I'm DupeBot.\n" + logo);
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break; // Exit the loop and end the program
            } else if (userInput.equalsIgnoreCase("list")) {
                // if user inputs list, then print out list if task list is not empty
                if(taskCounter == 0){
                    System.out.println("the task list is currently empty");
                }
                else {
                    for (int i = 0; i < taskCounter; i++){
                        System.out.println((i+1) + ". " + tasks[i]);
                    }
                }
            }
            else {
                // if user inputs anything else, then insert into list. check if list does not exceed 100
                if (taskCounter < 100) {
                    tasks[taskCounter] = userInput;
                    taskCounter++;
                    System.out.println("added: " + userInput);
                } else {
                    System.out.println("Sorry, your task list is full.");
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}