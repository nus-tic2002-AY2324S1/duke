import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printHelloWorld();

        Scanner scanner = new Scanner(System.in);
        System.out.print("");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            printRepeat(input);
            input = scanner.nextLine();
        }
        printByeWorld();
        scanner.close();
    }

    private static void formatter() {
        System.out.print("    ");
    }

    private static void printSeparator() {
        formatter();
        System.out.println("____________________________________________________________");
    }

    private static void printRepeat(String msg) {
        printSeparator();
        formatter();
        System.out.println(msg);
        printSeparator();
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
