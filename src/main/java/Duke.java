import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String input = "";
        do{
            input = in.next();
            if (input.equalsIgnoreCase("bye")){
                break;
            }
            line();
            System.out.println(input);
            line();
        }while(!input.equalsIgnoreCase("bye"));
        bye();
}

    private static void clearScreen(){
        System.out.print("\033");
    }

    private static void greet(){
        String myChatBotName = "Luna";

        line();
        logo();
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

    private static void logo(){
        String logo =
                  " _                    \n"
                + "| |    _   _ _  __     __\n"
                + "| |   | | | | | __  \\/ __ \\\n"
                + "| |___| |_| | |   | | |__| |\n"
                + "|_____|\\__,_|_|   |_|_|  |_|\n";
        System.out.println(logo);
    }

    private static void bye(){
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
}
