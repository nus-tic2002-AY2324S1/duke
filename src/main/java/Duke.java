import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String inputKey;
    private static String taskDescription;
    private static String by;
    private static String from;
    private static String to;

    public static void main(String[] args) {
        greet();
        processInput();
    }

    private static void processInput() {
        Scanner in = new Scanner(System.in);
        String input;
        ICommand command;
        do {
            input = in.nextLine();
            putInput(input);
            Keyword key = Keyword.getKeyword(inputKey);
            if(key!=null) {
                switch (key) {
                case BYE:
                    command = new Bye();
                    return;
                case LIST:
                    printList();
                    continue;
                case MARK:
                    processMark(input, true);
                    continue;
                case UNMARK:
                    processMark(input, false);
                    continue;
                case TODO:
                    tasks.add(new Todo(taskDescription));
                    break;
                case DEADLINE:
                    pullDeadlineDate(taskDescription);
                    tasks.add(new Deadline(taskDescription, by));
                    break;
                case EVENT:
                    pullEventDateTime(taskDescription);
                    tasks.add(new Event(taskDescription, from, to));
                    break;
                default:
                }
            }else{
                responseIDK();
            }
        } while (!input.equalsIgnoreCase("bye"));
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

        // Todo: handle the mark/unmark index out of range exception
        if (isMark) {
            markTask(index);
        } else {
            unMarkTask(index);
        }
    }

    private static void printList () {
        if(Task.getNumberOfTasks() == 0){
            line();
            indentation();
            System.out.println("Your list is empty. Let's start adding some items! :)");
            line();
            return;
        }

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

    private static void markTask (int index) {
        line();
        indentation();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markAsDone(true);
        indentation();
        System.out.println(tasks.get(index - 1));
        line();
    }

    private static void unMarkTask (int index) {
        line();
        indentation();
        System.out.println("Ok, I've marked this task as not done yet:");
        tasks.get(index - 1).markAsDone(false);
        indentation();
        System.out.println(tasks.get(index - 1));
        line();
    }

    private static void pullDeadlineDate (String input){
        final String BY = "/by";
        int len = BY.length();
        int pos = -1;

        pos = input.indexOf(BY);

        //Todo: throw IllegalInputException here
        /*if(pos == -1){
            throw new IllegalInputException();
        }*/

        by = input.substring(pos + len).trim();
        taskDescription = input.substring(0,pos).trim();
    }

    private static void pullEventDateTime(String input){
        final String FROM = "/from";
        final String TO = "/to";
        int lenOfFrom = FROM.length();
        int lenOfTo = TO.length();
        int posOfFrom = -1;
        int posOfTo = -1;

        posOfFrom = input.indexOf(FROM);
        posOfTo = input.indexOf(TO);

        //Todo: throw IllegalInputException here
        /*if(posOfFrom == -1 || posOfTo == -1){
            throw new IllegalInputException();
        }*/

        from = input.substring(posOfFrom + lenOfFrom, posOfTo).trim();
        to = input.substring(posOfTo + lenOfTo).trim();
        taskDescription = input.substring(0,posOfFrom).trim();
    }

    private static void putInput(String input){
        String[] inputs = input.split(" ");
        inputKey = inputs[0];
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

    private static void greet() {
        String myChatBotName = "Luna";
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(String.format("Hello! I'm %s", myChatBotName));
        inputs.add("What can I do for you?");
        Conversation.echo(logo());
        Conversation.echoForGreet(inputs);
    }

    public static void line() {
        String horizontalBox = "*";
        String line = horizontalBox.repeat(80);
        System.out.print("    ");
        System.out.println(line);
    }

    private static String logo() {
        String logo;
        logo = " _                    \n"
                + "     | |    _   _ _  __     __\n"
                + "     | |   | | | | | __  \\/ __ \\\n"
                + "     | |___| |_| | |   | | |__| |\n"
                + "     |_____|\\__,_|_|   |_|_|  |_|\n";
        return logo;
    }
}
