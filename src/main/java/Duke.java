import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> userInputs = new ArrayList<>();
    final static String BYE = "bye";
    final static  String LIST = "list";
    public static void main(String[] args) {
        clearScreen();
        greet();
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase(BYE)) {
                break;
            }else if(input.equalsIgnoreCase(LIST)){
                printList();
                continue;
            }
            userInputs.add(input);
            line();
            echoAdd(input);
            line();
        } while (!input.equalsIgnoreCase(BYE));
        bye();
    }

    private static void printList(){
        line();
        int count = 0;
        for (String userInput : userInputs) {
            count++;
            userInput = String.format("%d. %s",count, userInput);
            echo(userInput);
        }
        line();
    }

    private static void indentation() {
        System.out.print("     ");
    }

    private static void echo(String input){
        indentation();
        System.out.println(input);
    }

    private static void echoAdd(String input){
        indentation();
        System.out.print("added: ");
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
        indentation();
        System.out.printf("Hello! I'm %s\n", myChatBotName);
        indentation();
        System.out.println("What can I do for you?");
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
