package seedu.duke.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import seedu.duke.commands.WonkyMode;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.task.Task;

/**
 * The WonkyLogger class provides logging and printing functionality for the Wonky Bot application.
 * It includes methods for logging errors and warnings, printing lists of tasks, and initializing the application.
 * The class also includes constants for error and warning messages, and a logo for the application.
 */
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

    private static final List<String> UNKNOWN_CMD_MSGS = new ArrayList<>(
        Arrays.asList(
            "Oops! I do not understand the command [%s].",
            "Sorry, the command [%s] you entered does not exist.",
            "My vocabulary of command(s) does not include [%s]."
        )
    );

    private static final List<String> MISMATCH_ARG_MSGS = new ArrayList<>(
        Arrays.asList(
            "Oops! Seems like your argument(s) for command [%s] are wrong.",
            "Sorry, the argument(s) for this command [%s] are incorrect.",
            "Please check your argument(s) for this command [%s]."
        )
    );

    private static final List<String> SUGGEST_CMD_MSGS = new ArrayList<>(
        Arrays.asList(
            "Did you mean to type command [%s] instead?"
        )
    );

    private static final String EXPECTED_ARG_MSG =
        "Did you input the wrong argument(s)? Expected [%d] argument(s) for the command.";
    private static final String EXPECTED_TYPE_MSG =
        "There is mistake with the argument. Expected [%s] instead of [%s].";

    private static final String ADD_TO_LIST_MSG =
        "I have added %s task [%s] to our list!";

    private static final String MARK_TYPO_MSG =
        "Did you mark/unmark the wrong task? %s is already set to [%s].";
    private static final String TASK_MARKED_MSG =
        "Your task [%s] is marked as [%s].";

    private static final String DELETE_INVALID_MSG =
        "Did you make a mistake trying to delete task [%d]? A task can't be be less than 1.";
    private static final String DELETE_TYPO_MSG =
        "Did you want to delete the wrong task? There is no task no. [%d].";
    private static final String TASK_DELETED_MSG =
        "Your task [%s] is deleted!";

    private static final Random RND = new Random();

    private static boolean hasError = false;
    private static WonkyMode mode = WonkyMode.NORMAL;
    private static boolean isLoading = false;

    private static void printlnWithWonky(String toPrint) throws DukeLoggerException {
        if (!isLoading) {
            println("Wonky: " + toPrint);
        }
    }

    private static void printWarnWithWonky(String toPrint) throws DukeLoggerException {
        hasError = true;
        if (isLoading) {
            println("Wonky: Uhoh seems like there was an issue when loading the storage. " + toPrint);
        } else {
            println("Wonky: " + toPrint);
        }
    }

    private static void println(String toPrint) throws DukeLoggerException {
        try {
            System.out.println("\t" + toPrint);
        } catch (Exception e) {
            throw new DukeLoggerException(e);
        }
    }

    private static void printListTitle(int size) throws DukeLoggerException {
        if (size == 0) {
            printlnWithWonky("Hooray you have no pending tasks!");
        } else {
            printlnWithWonky("Below are the list of tasks you have added!");
        }
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to print.
     * @throws DukeLoggerException If there is an error with the logger.
     */
    public static void printListCommand(List<Task> tasks) throws DukeLoggerException {
        printListTitle(tasks.size());
        if (!isLoading) {
            for (int i = 0; i < tasks.size(); i += 1) {
                WonkyLogger.task(tasks.get(i).getStatusMsg(i + 1));
            }
        }
    }

    /**
     * Prints log message for the start of the application.
     * 
     * @param modeToSet
     * @throws DukeLoggerException
     */
    public static void startUp(WonkyMode modeToSet) throws DukeLoggerException {
        mode = modeToSet;
        if (hasError) {
            println("");
            printlnWithWonky("Initialisation completed with warnings.");
        } else {
            printlnWithWonky("Initialisation completed successfully.");
        }
        println("");
        printlnWithWonky("Hello from\n" + LOGO);
        printlnWithWonky("I'm Wonky the Fairy.");
        printlnWithWonky("What can I do for you?");
    }

    /**
     * Prints log message for the initialisation of the storage.
     * 
     * @param isNew
     * @throws DukeLoggerException
     */
    public static void initialiseStorage(boolean isNew) throws DukeLoggerException {
        if (isNew) {
            println("Wonky: Initialising...");
        } else {
            println("Wonky: Initialising and loading data from storage...");
            println(" ");
        }
    }

    /**
     * Prints log message for a task.
     * 
     * @param task
     * @throws DukeLoggerException
     */
    public static void task(String task) throws DukeLoggerException {
        println("\t" + task);
    }

    /**
     * Prints log message for a task added to the list.
     * 
     * @param task
     * @param desc
     * @throws DukeLoggerException
     */
    public static void addedToList(String task, String desc) throws DukeLoggerException {
        printlnWithWonky(String.format(ADD_TO_LIST_MSG, task, desc));
    }

    /**
     * Prints log message for the bye command.
     * 
     * @throws DukeLoggerException
     */
    public static void bye() throws DukeLoggerException {
        printlnWithWonky("Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
    }

    /**
     * Prints log message for an unknown command.
     * 
     * @param cmd
     * @throws DukeLoggerException
     */
    public static void unknownCommand(String cmd) throws DukeLoggerException {
        printWarnWithWonky(
            String.format(
                randomFromArray(UNKNOWN_CMD_MSGS),
                cmd
            )
        );
    }

    /**
     * Prints log message for a mismatched argument.
     * 
     * @param cmd
     * @throws DukeLoggerException
     */
    public static void mismatchArgs(String cmd) throws DukeLoggerException {
        printWarnWithWonky(
            String.format(
                randomFromArray(MISMATCH_ARG_MSGS),
                cmd
            )
        );
    }

    /**
     * Prints log message for a mismatched argument with the expected argument count.
     * 
     * @param cmd
     * @param expectedArgCount
     * @throws DukeLoggerException
     */
    public static void mismatchArgs(String cmd, int expectedArgCount) throws DukeLoggerException {
        mismatchArgs(cmd);
        printWarnWithWonky(String.format(EXPECTED_ARG_MSG, expectedArgCount));
    }

    /**
     * Prints log message for an expected integer argument type.
     * 
     * @param val
     * @throws DukeLoggerException
     */
    public static void expectedInteger(String val) throws DukeLoggerException {
        printWarnWithWonky(String.format(EXPECTED_TYPE_MSG, "Integer", val));
    }

    /**
     * Prints log message for an expected date argument type.
     * 
     * @param val
     * @throws DukeLoggerException
     */
    public static void expectedDateTime(String val) throws DukeLoggerException {
        printWarnWithWonky(String.format(EXPECTED_TYPE_MSG, "yyyy-MM-dd HH:mm", val));
    }

    /**
     * Prints log message to suggest a command.
     * 
     * @param cmd
     * @throws DukeLoggerException
     */
    public static void suggestCommand(String cmd) throws DukeLoggerException {
        if (Objects.nonNull(cmd)) {
            printWarnWithWonky(
                String.format(
                    randomFromArray(SUGGEST_CMD_MSGS),
                    cmd
                )
            );
        }
    }

    /**
     * Prints log message for a typo in marking a task.
     * 
     * @param desc
     * @param isDoneLitr
     * @throws DukeLoggerException
     */
    public static void markTypo(String desc, String isDoneLitr) throws DukeLoggerException {
        printWarnWithWonky(String.format(MARK_TYPO_MSG, desc, isDoneLitr));
    }

    /**
     * Prints log message for marking a task.
     * 
     * @param desc
     * @param isDoneLitr
     * @throws DukeLoggerException
     */
    public static void taskMarked(String desc, String isDoneLitr) throws DukeLoggerException {
        printlnWithWonky(String.format(TASK_MARKED_MSG, desc, isDoneLitr));
    }

    /**
     * Prints log message for a typo in deleting a task.
     * 
     * @param idx
     * @throws DukeLoggerException
     */
    public static void deleteTypo(int idx) throws DukeLoggerException {
        String msg = DELETE_TYPO_MSG;
        if (idx <= 0) {
            msg = DELETE_INVALID_MSG;
        }
        printWarnWithWonky(String.format(msg, idx));
    }

    /**
     * Prints log message for deleting a task.
     * 
     * @param desc
     * @throws DukeLoggerException
     */
    public static void taskDeleted(String desc) throws DukeLoggerException {
        printlnWithWonky(String.format(TASK_DELETED_MSG, desc));
    }

    /**
     * Returns isLoading.
     * 
     * @param bool
     */
    public static void setIsLoading(boolean bool) {
        isLoading = bool;
    }

    /**
     * Prints log message for the bye command not supposed to be in storage.
     * 
     * @throws DukeLoggerException
     */
    public static void byeInStorage() throws DukeLoggerException {
        printWarnWithWonky("Bye command should not be stored.");
    }

    /**
     * Returns mode.
     * 
     * @return
     */
    public static WonkyMode getMode() {
        return mode;
    }

    /**
     * Returns isLoading.
     * 
     * @return
     */
    public static boolean getLoading() {
        return isLoading;
    }

    private static String randomFromArray(List<String> choices) {
        if (WonkyMode.TEST.equals(mode)) {
            return choices.get(0);
        }
        return choices.get(RND.nextInt(choices.size()));
    }
}
