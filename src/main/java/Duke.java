import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private String keyword;
    private String taskDescription;

    public static void main(String[] args) {
        greet();
        processInput();
        bye();
    }

    private static void processInput() {
        Scanner in = new Scanner(System.in);
        String input;
        Duke duke = new Duke();
        do {
            input = in.nextLine();
//            String inputKey = askKeyword(input);
            duke.locateInput(input);
            Keyword key = Keyword.getKeyword(duke.keyword);
            boolean isMark;
            if(key!=null) {
                switch (key) {
                case BYE:
                    return;
                case LIST:
                    printList();
                    continue;
                case MARK:
                    isMark = true;
                    processMark(input, isMark);
                    continue;
                case UNMARK:
                    isMark = false;
                    processMark(input, isMark);
                    continue;
                case TODO:
                    tasks.add(new Todo(duke.taskDescription));
                    break;
                case DEADLINE:
                    break;
                case EVENT:
                    break;
                default:
                }
            }else{
                responseIDK();
            }
        } while (!input.equalsIgnoreCase(Keyword.BYE.name()));
    }

    private static void responseIDK() {
        line();
        indentation();
        System.out.println("I'm sorry, I'm not sure what you're asking.");
        line();
    }

    private static void processMark(String input, boolean isMark) {
        String[] inputs = input.split(" ");
        int index = -1;
        // Todo: handle the mark/unmark without number and out of range error
        try {
            index = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index number for the mark item.");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.printf("The number after the %s is mandatory!\n",isMark?"Mark":"Unmark");
        }

        if (index == -1) {
            return;
        }
        if (isMark) {
            markTask(index);
        } else {
            unMarkTask(index);
        }
    }

    private static void printList() {
        line();
        indentation();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            indentation();
            System.out.print(i + ".");
            System.out.println(tasks.get(i - 1));
        }
        line();
    }

    private static void markTask(int index) {
        line();
        indentation();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markAsDone(true);
        indentation();
        System.out.println(tasks.get(index - 1));
        line();
    }

    private static void unMarkTask(int index) {
        line();
        indentation();
        System.out.println("Ok, I've marked this task as not done yet:");
        tasks.get(index - 1).markAsDone(false);
        indentation();
        System.out.println(tasks.get(index - 1));
        line();
    }

    private static String askKeyword(String input) {
        String[] inputs = input.split(" ");
        return inputs[0];
    }

    private void locateInput (String input){
        String[] inputs = input.split(" ");
        keyword = inputs[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            sb.append(inputs[i]);
            sb.append(" ");
        }
        taskDescription = sb.toString().trim();

    }

    public static void indentation() {
        System.out.print("     ");
    }

    private static void echo(String input) {
        indentation();
        System.out.println(input);
    }

    private static void echoAdd(String input) {
        indentation();
        System.out.print("added: ");
        System.out.println(input);
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

    public static void line() {
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
