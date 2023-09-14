import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static int count = 0;
    private static String[] list = new String[100];

    public static void main(String[] args) {
        printHelloWorld();

        Scanner scanner = new Scanner(System.in);
        System.out.print("");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                printList();
            } else {
                handleInput(input);
            }
            input = scanner.nextLine();
        }
        printByeWorld();
        scanner.close();
    }

    private static void formatter() {
        System.out.print("    ");
    }

    private static void printList() {
        printSeparator();
        for (int i = 0; i < count; i++) {
            formatter();
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(list[i]);
        }
        printSeparator();
    }

    private static void handleInput(String input) {
        printSeparator();
        formatter();
        list[count] = input;
        count++;
        System.out.println("added: " + input);
        printSeparator();
    }

    private static void printSeparator() {
        formatter();
        System.out.println("____________________________________________________________");
    }

    private static void printHelloWorld() {
        printSeparator();
        formatter();
        System.out.println("Hello! I'm AngelBot!");
        formatter();
        System.out.println("What can I do for you?");
        printSeparator();
    }

    private static void printByeWorld() {
        printSeparator();
        formatter();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();

    }
}
