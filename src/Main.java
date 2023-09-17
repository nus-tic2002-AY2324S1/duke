// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        Scanner scanner = new Scanner(System.in);

        // Greet the user
        String logo = "    ____                       __\n" +
                "   / __ \\__  ______  ___  ____/ /\n" +
                "  / / / / / / / __ \\/ _ \\/ __  /\n" +
                " / /_/ / /_/ / /_/ /  __/ /_/ /\n" +
                "/_____/\\__,_/ .___/\\___/\\__,_/\n" +
                "           /_/";
        System.out.println("Hello! I'm DupeBot.\n" + logo);
        System.out.println("What can I do for you?");

        // Read user input
        String userInput = scanner.nextLine();

        // Check if the user wants to exit
        if (userInput.equalsIgnoreCase("exit")) {
            // Say goodbye and exit the program
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            // For any other input, you can provide a response (you can expand on this)
            System.out.println("I'm a simple chatbot and the chat ends here. Bye.");
        }

        // Close the scanner
        scanner.close();
    }
}