package Task;

import java.util.Scanner;

/**
 * List of custom text for the UI
 * Non-simple text output is store here and called in the main program
 */
public class Text { //aka UI
    public static final String newline = "____________________________________________________________\n";

    public enum Message {
        LOGO, GREETING, BYE, NOITEM
    }

    private static final String LOGO = "Loading...\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String CLARA = "...\n"
            + "   ____ _        _    ____      _     \n"
            + "  / ___| |      / \\  |  _ \\    / \\    \n"
            + " | |   | |     / _ \\ | |_) |  / _\\   \n"
            + " | |___| |___ / ___ \\|  _ <  / ___ \\  \n"
            + "  \\____|_____/_/   \\_\\_| \\_\\/_/   \\_\\ \n";

    private static final String GREETING = newline +
            " Hello! I'm CLARA \n" +
            " What can I do for you?\n" +
            newline;
    private static final String BYE = newline +
            " Bye. Hope to see you again soon!\n" +
            newline;

    private static final String NOITEM = newline +
            "No items found!, Please add items" + "\n" +
            newline;

    public static final String NOMATCHINGTASK = newline +
            "There are no matches found with this keyword." + "\n" +
            "Please use another keyword instead" + "\n" +
            newline;
    public static final String MATCHINGTASK = newline +
            "Here are the matching tasks in your list:" + "\n";

    public static void showWelcome() {
        printMessage(Text.Message.LOGO);
        printMessage(Text.Message.GREETING);
    }

    public static void showLoadingError() {
        System.out.println("Error loading the file, please restart the program!");
    }

    public static void printMessage(Message message) {
        switch (message) {
            case LOGO:
                System.out.println(CLARA);
                break;
            case GREETING:
                System.out.println(GREETING);
                break;
            case BYE:
                System.out.println(BYE);
                break;
            case NOITEM:
                System.out.println(NOITEM);
                break;
            default:
                throw new IllegalArgumentException("Invalid message type: " + message);
        }
    }

    public static String showPrompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        String input = in.nextLine();
        return input;
    }
    public static Boolean fieldChecker(String[] input) {
        boolean check;
        switch (input[0]) {
            case "edit":
                if (input.length < 2) {
                    System.out.println("Please include the task number you want to edit e.g.(edit 2)");
                    System.out.println(Text.newline);
                    check = false;
                } else {
                    check = true;
                }
                break;
            default:
                check = true;
        }
        return check;
    }
}

