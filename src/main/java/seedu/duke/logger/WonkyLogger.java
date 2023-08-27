package seedu.duke.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    private static final List<String> unknownCmdMsgs = new ArrayList<>(
        Arrays.asList(
            "Oops! I do not understand the command [%s].",
            "Sorry, the command [%s] you entered does not exist.",
            "My vocabulary of commands does not include [%s]."
        )
    );

    private static final List<String> suggestCmdMsgs = new ArrayList<>(
        Arrays.asList(
            "Did you mean to type command [%s] instead?"
        )
    );

    private static final Random rnd = new Random();

    private static void printlnWithWonky(String toPrint) throws DukeLoggerException {
        println("Wonky: " + toPrint);
    }

    private static void println(String toPrint) throws DukeLoggerException {
        try {
            System.out.println("\t" + toPrint);
        } catch (Exception e) {
            throw new DukeLoggerException(e.getMessage());
        }
    }

    public static void startUp() throws DukeLoggerException {
        printlnWithWonky("Hello from\n" + LOGO);
        printlnWithWonky("I'm Wonky the Fairy.");
        printlnWithWonky("What can I do for you?");
    }

    public static void task(String task) throws DukeLoggerException {
        println("\t" + task);
    }

    public static void postList() throws DukeLoggerException {
        printlnWithWonky("Below are the list of items you have added!");
    }

    public static void preList(String line) throws DukeLoggerException {
        printlnWithWonky("I have added " + "[" + line + "] to our list!");
    }

    public static void bye() throws DukeLoggerException {
        printlnWithWonky("Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
    }

    public static void unknownCommand(String cmd) throws DukeLoggerException {
        printlnWithWonky(
            String.format(
                unknownCmdMsgs.get(
                    rnd.nextInt(unknownCmdMsgs.size())
                ), cmd
            )
        );
    }

    public static void suggestCommand(String cmd) throws DukeLoggerException {
        if (Objects.nonNull(cmd)) {
            printlnWithWonky(
                String.format(
                    suggestCmdMsgs.get(
                        rnd.nextInt(suggestCmdMsgs.size())
                    ), cmd
                )
            );
        }
    }
}
