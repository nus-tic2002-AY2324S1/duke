package seedu.duke.logger;

import seedu.duke.exceptions.DukeLoggerException;

public class WonkyLogger {

    private static final String LOGO =
        "\t__          __         _            ____        _   \n"
        + "\t\\ \\        / /        | |          |  _ \\      | |  \n"
        + "\t \\ \\  /\\  / /__  _ __ | | ___   _  | |_) | ___ | |_ \n"
        + "\t  \\ \\/  \\/ / _ \\| '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
        + "\t   \\  /\\  / (_) | | | |   <| |_| | | |_) | (_) | |_ \n"
        + "\t    \\/  \\/ \\___/|_| |_|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
        + "\t                             __/ |                  \n"
        + "\t                            |___/                  \n";

    private static void println(String toPrint) throws DukeLoggerException {
        try {
            System.out.println("\t" + toPrint);
        } catch (Exception e) {
            throw new DukeLoggerException(e.getMessage());
        }
    }

    public static void startUp() throws DukeLoggerException {
        println("Wonky: Hello from\n" + LOGO);
        println("Wonky: I'm Wonky the Fairy.");
        println("Wonky: What can I do for you?");
    }

    public static void task(String task) throws DukeLoggerException {
        println("\t" + task);
    }

    public static void postList() throws DukeLoggerException {
        println("Wonky: Below are the list of items you have added!");
    }

    public static void preList(String line) throws DukeLoggerException {
        println("Wonky: I have added " + "[" + line + "] to our list!");
    }

    public static void bye() throws DukeLoggerException {
        println("Wonky: Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
    }
}
