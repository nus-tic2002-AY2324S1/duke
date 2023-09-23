import java.util.MissingFormatArgumentException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String inputKey;
    private static String dukeDescription;

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
                    command = key.createCommand();
                    return;
                case LIST:
                    command = key.createCommand();
                    continue;
                case MARK:
                    command = new Mark(dukeDescription);
                    continue;
                case UNMARK:
                    command = new UnMark(dukeDescription);
                    continue;
                case TODO:
                    tasks.add(new Todo(dukeDescription));
                    break;
                case DEADLINE:
                    tasks.add(new Deadline(dukeDescription));
                    break;
                case EVENT:
                    tasks.add(new Event(dukeDescription));
                    break;
                case DELETE:
                    command = new Delete(dukeDescription);
                    break;
                default:

                }
            } catch (IllegalArgumentException e) {
                responseIDK();
            }catch (MissingDescriptionException e){
                Conversation.echo((e.getMessage()));
            }
        } while (!input.equalsIgnoreCase("bye"));
    }

    private static void responseIDK() {
        String str = "OOPS!!! I'm sorry, but I'm not sure what you're asking :-(";
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(str);
        inputs.add("You can use the commands that are currently available.");
        inputs.addAll(commandList());
        Conversation.echo(inputs);
    }

    private static ArrayList<String> commandList(){
        ArrayList<String> out = new ArrayList<>();
        out.add("1. bye");
        out.add("2. list");
        out.add("3. mark - [use example: mark 1]");
        out.add("4. unmark - [use example: unmark 1]");
        out.add("5. todo - [use example: todo borrow book]");
        out.add("6. deadline - [use example: deadline return book /by Sunday]");
        out.add("7. event - [use example: event project meeting /from 12pm 16th Aug /to 15pm 17th Aug");
        return out;
    }

    private static void putInput(String input)  {
        String[] inputs = input.split(" ");
        inputKey = inputs[0];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            sb.append(inputs[i]);
            sb.append(" ");
        }
        dukeDescription = sb.toString().trim();
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
    public String getInputKey(){
        return inputKey;
    }

}
