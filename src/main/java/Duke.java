import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        clearScreen();
        greet();
        Scanner in = new Scanner(System.in);
        String input = "";
        do {
            input = in.next();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            line();
            echo(input);
            line();
        } while (!input.equalsIgnoreCase("bye"));
        bye();
    }

    private static void indentation() {
        System.out.print("     ");
    }

    private static void echo(String input){
        indentation();
        System.out.println(input);
    }

    private static void clearScreen() {
        System.out.println("\033");
    }

    private static void greet() {
        String myChatBotName = "Luna";

        line();
        logo();
        line();
        String str = String.format("Hello! I'm %s\n", myChatBotName);
        echo(str);
        str = "What can I do for you?";
        echo(str);
        line();
    }

    private static void line() {
        String horizontalBox = "─";
        String line = horizontalBox.repeat(80);
        System.out.print("    ");
        System.out.println(line);
    }

    private static void logo() {
        String logo =
                "      _                    \n"
                + "     | |    _   _ _  __     __\n"
                + "     | |   | | | | | __  \\/ __ \\\n"
                + "     | |___| |_| | |   | | |__| |\n"
                + "     |_____|\\__,_|_|   |_|_|  |_|\n";
        System.out.println(logo);
    }

    private static void bye() {
        String str = "Bye. Hope to see you again soon!";
        line();
        echo(str);
        line();
    }
}
