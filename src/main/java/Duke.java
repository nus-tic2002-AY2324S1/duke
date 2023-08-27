import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.next();
        System.out.println(input);
        bye();
}

    private static void clearScreen(){
        System.out.print("\033");
    }

    private static void greet(){
        String myChatBotName = "Luna";

        line();
        System.out.println(logo());
        line();
        System.out.printf("Hello! I'm %s\n", myChatBotName);
        System.out.println("What can I do for you?");
        line();
    }

    private static void line () {
        String horizontalBox = "─";
        String line = horizontalBox.repeat(80);
        System.out.println(line);
    }

    private static String logo(){
        String logo =
                  " _                    \n"
                + "| |    _   _ _  __     __\n"
                + "| |   | | | | | __  \\/ __ \\\n"
                + "| |___| |_| | |   | | |__| |\n"
                + "|_____|\\__,_|_|   |_|_|  |_|\n";
        return logo;
    }

    private static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
