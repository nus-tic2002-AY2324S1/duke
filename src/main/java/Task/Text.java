package Task;

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
    public static void showWelcome(){
        printMessage(Text.Message.LOGO);
        printMessage(Text.Message.GREETING);
    }
    public static void showLoadingError(){
        System.out.println("Error loading the file, please restart the program!");
    }
    public static void printMessage(Message message) {
        switch (message) {
            case LOGO:
                System.out.println(LOGO);
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

}
