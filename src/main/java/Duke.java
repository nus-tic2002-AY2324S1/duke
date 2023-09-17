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
            try {
                KeywordTypes key = KeywordTypes.valueOf(inputKey.toUpperCase());
                switch (key) {
                case BYE:
                    command = new Bye();
                    return;
                case LIST:
                    command = new List();
                    continue;
                case MARK:
                    command = new Mark(taskDescription);
                    continue;
                case UNMARK:
                    command = new Unmark(taskDescription);
                    continue;
                case TODO:
                    command = new Todo(taskDescription);
                    tasks.add((Task)command);
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
            } catch (IllegalArgumentException e) {
                responseIDK();
            }
        } while (!input.equalsIgnoreCase("bye"));
    }

    private static void responseIDK() {
        String str = "OOPS!!! I'm sorry, but I'm not sure what you're asking :-(";
        Conversation.echo(str);
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
        Conversation.echoWithBottomLine(inputs);
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

    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
