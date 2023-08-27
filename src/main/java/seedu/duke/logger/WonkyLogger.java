package seedu.duke.logger;

public class WonkyLogger {

    private static final String LOGO =
        "__          __         _            ____        _   \n"
        + "\\ \\        / /        | |          |  _ \\      | |  \n"
        + " \\ \\  /\\  / /__  _ __ | | ___   _  | |_) | ___ | |_ \n"
        + "  \\ \\/  \\/ / _ \\| '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
        + "   \\  /\\  / (_) | | | |   <| |_| | | |_) | (_) | |_ \n"
        + "    \\/  \\/ \\___/|_| |_|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
        + "                             __/ |                  \n"
        + "                            |___/                  \n";

    private static void println(String toPrint) {
        System.out.println("\t" + toPrint);
    }

    public static void startUp() {
        println("Wonky: Hello from\n" + LOGO);
        println("Wonky: I'm Wonky the Fairy.");
        println("Wonky: What can I do for you?");
    }

    public static void task(String task) {
        println("\t" + task);
    }

    public static void postList() {
        println("Wonky: Below are the list of items you have added!");
    }

    public static void preList(String line) {
        println("Wonky: I have added " + "[" + line + "] to our list!");
    }

    public static void bye() {
        println("Wonky: Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
    }
}
