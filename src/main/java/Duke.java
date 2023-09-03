import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    final static String BYE = "bye";
    final static  String LIST = "list";
    final static String MARK = "mark";
    final static String UNMARK = "unmark";
    public static void main(String[] args) {
        clearScreen();
        greet();
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            if (askKeyword(input).equalsIgnoreCase(BYE)) {
                break;
            }else if(askKeyword(input).equalsIgnoreCase(LIST)){
                printList();
                continue;
            }else if(askKeyword(input).equalsIgnoreCase(MARK)){
                String[] inputs = input.split(" ");
                int index = -1;
                // Todo: handle the mark/unmark without number and out of range error
                try{
                    index = Integer.parseInt(inputs[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid index number for the mark item.");
                }
                if(index != -1) {
                    markTask(index);
                }
                continue;
            }else if(askKeyword(input).equalsIgnoreCase((UNMARK))){
                String[] inputs = input.split(" ");
                int index = -1;
                // Todo: handle the mark/unmark without number and out of range error
                try{
                    index = Integer.parseInt(inputs[1]);
                }catch (NumberFormatException e){
                    System.out.println("Invalid index number for the unmark item.");
                }
                if(index != -1) {
                    unMarkTask(index);
                }
                continue;
            }
            tasks.add(new Task(input));
            line();
            echoAdd(input);
            line();
        } while (!input.equalsIgnoreCase(BYE));
        bye();
    }

    private static void printList(){
        line();
        indentation();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            indentation();
            System.out.print(i + " ");
            System.out.println(tasks.get(i-1));
        }
        line();
    }

    private static void markTask(int index){
        line();
        indentation();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index-1).markAsDone(true);
        indentation();
        System.out.println(tasks.get(index-1));
        line();
    }

    private static void unMarkTask(int index){
        line();
        indentation();
        System.out.println("Ok, I've marked this task as not done yet:");
        tasks.get(index-1).markAsDone(false);
        indentation();
        System.out.println(tasks.get(index-1));
        line();
    }

    private static String askKeyword(String input){
        String[] inputs = input.split(" ");
        return inputs[0];
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
        String horizontalBox = "*";
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
