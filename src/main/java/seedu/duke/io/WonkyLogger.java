package seedu.duke.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.helper.WonkyMode;
import seedu.duke.task.Task;

/**
 * Provides logging and printing functionality for the Wonky Bot application.
 * It includes methods for logging errors and warnings, printing lists of tasks, and initializing the application.
 * The class also includes constants for error and warning messages, and a logo for the application.
 */
public class WonkyLogger {

    private final String LOGO =
        "\t__          __         _            ____        _   " + System.lineSeparator()
        + "\t\\ \\        / /        | |          |  _ \\      | |  " + System.lineSeparator()
        + "\t \\ \\  /\\  / /__  _ __ | | ___   _  | |_) | ___ | |_ " + System.lineSeparator()
        + "\t  \\ \\/  \\/ / _ \\| '_ \\| |/ / | | | |  _ < / _ \\| __|" + System.lineSeparator()
        + "\t   \\  /\\  / (_) | | | |   <| |_| | | |_) | (_) | |_ " + System.lineSeparator()
        + "\t    \\/  \\/ \\___/|_| |_|_|\\_\\\\__, | |____/ \\___/ \\__|" + System.lineSeparator()
        + "\t                             __/ |                  " + System.lineSeparator()
        + "\t                            |___/                  " + System.lineSeparator();

    private final List<String> UNKNOWN_CMD_MSGS = new ArrayList<>(
        Arrays.asList(
            "Oops! I do not understand the command [%s].",
            "Sorry, the command [%s] you entered does not exist.",
            "My vocabulary of command(s) does not include [%s]."
        )
    );

    private final List<String> MISMATCH_ARG_MSGS = new ArrayList<>(
        Arrays.asList(
            "Oops! Seems like your argument(s) for command [%s] are wrong.",
            "Sorry, the argument(s) for this command [%s] are incorrect.",
            "Please check your argument(s) for this command [%s]."
        )
    );

    private final List<String> SUGGEST_CMD_MSGS = new ArrayList<>(
        Arrays.asList(
            "Did you mean to type command [%s] instead?"
        )
    );

    private final String EXPECTED_ARG_MSG =
        "Did you input the wrong argument(s)? Expected [%d] argument(s) for the command.";
    private final String EXPECTED_TYPE_MSG =
        "There is mistake with the argument. Expected [%s] instead of [%s].";

    private final String ADD_TO_LIST_MSG =
        "I have added %s task [%s] to our list!";

    private final String ALREADY_MARK_MSG =
        "Did you mark/unmark the wrong task? %s is already set to [%s].";
    private final String TASK_MARKED_MSG =
        "Your task [%s] is marked as [%s].";

    private final String INVALID_MSG =
        "Did you make a mistake trying to %s task [%d]? A task can't be be less than 1.";
    private final String TYPO_MSG =
        "Did you want to %s the wrong task? There is no task no. [%d].";
    private final String TASK_DELETED_MSG =
        "Your task [%s] is deleted!";
    private final String TASK_NOT_EXIST_MSG =
        "The task [%d] does not exist!";

    private final String HELP_TEXT =
        "Below are the list of commands you can use!" + System.lineSeparator() + System.lineSeparator()
        + "\tlist" + System.lineSeparator()
        + "\t\tLists all the tasks." + System.lineSeparator() + System.lineSeparator()
        + "\thelp" + System.lineSeparator()
        + "\t\tPrints the list of commands you can use!" + System.lineSeparator() + System.lineSeparator()
        + "\tbye" + System.lineSeparator()
        + "\t\tExits the application." + System.lineSeparator() + System.lineSeparator()
        + "\ttodo {desc}" + System.lineSeparator()
        + "\t\tAdds a todo task to the list." + System.lineSeparator() + System.lineSeparator()
        + "\tdeadline {desc} | {by}" + System.lineSeparator()
        + "\t\tAdds a deadline task to the list." + System.lineSeparator() + System.lineSeparator()
        + "\tevent {desc} | {from} | {to}" + System.lineSeparator()
        + "\t\tAdds an event task to the list." + System.lineSeparator() + System.lineSeparator()
        + "\tmark {taskNo}" + System.lineSeparator()
        + "\t\tMarks a task as done." + System.lineSeparator() + System.lineSeparator()
        + "\tunmark {taskNo}" + System.lineSeparator()
        + "\t\tUnmarks a task as done." + System.lineSeparator() + System.lineSeparator()
        + "\tdelete {taskNo}" + System.lineSeparator()
        + "\t\tDeletes a task from the list." + System.lineSeparator() + System.lineSeparator()
        + "\tfind {desc}" + System.lineSeparator()
        + "\t\tFinds tasks containing the given description." + System.lineSeparator() + System.lineSeparator()
        + "\tstash list" + System.lineSeparator()
        + "\t\tShows the list of current stashes." + System.lineSeparator() + System.lineSeparator()
        + "\tstash clear" + System.lineSeparator()
        + "\t\tClears all of current stashes." + System.lineSeparator() + System.lineSeparator()
        + "\tstash add | {stashName}" + System.lineSeparator()
        + "\t\tAdds the current lists of tasks to a stash." + System.lineSeparator() + System.lineSeparator()
        + "\tstash apply | {stashName}" + System.lineSeparator()
        + "\t\tApplies the stash to the current list of tasks." + System.lineSeparator() + System.lineSeparator()
        + "\tstash pop | {stashName}" + System.lineSeparator()
        + "\t\tPops the stash to the current list of tasks." + System.lineSeparator() + System.lineSeparator();

    private final Random RND = new Random();

    private boolean hasError = false;
    private WonkyMode mode = WonkyMode.NORMAL;
    private boolean isLoading = false;

    private String response = "";

    private static WonkyLogger instance;

    public WonkyLogger() {}

    public static WonkyLogger getInstance() {
        if (instance == null) {
            instance = new WonkyLogger();
        }
        return instance;
    }

    private void padLine() throws DukeLoggerException {
        println("");
    }

    private void printlnWithWonky(String toPrint) throws DukeLoggerException {
        assert toPrint != null : "String to print should not be null";
        if (!isLoading) {
            println("Wonky: " + toPrint);
        }
    }

    private void printWarnWithWonky(String toPrint) throws DukeLoggerException {
        assert toPrint != null : "String to print should not be null";
        hasError = true;
        if (isLoading) {
            println("Wonky: Uhoh seems like there was an issue when loading the storage. " + toPrint);
        } else {
            println("Wonky: " + toPrint);
        }
    }

    private void println(String toPrint) throws DukeLoggerException {
        try {
            response = response + System.lineSeparator() + toPrint;
        } catch (Exception e) {
            throw new DukeLoggerException(e);
        }
    }

    private void printListTitle(int size) throws DukeLoggerException {
        assert size >= 0 : "Size should not be negative";
        if (size == 0) {
            printlnWithWonky("Hooray you have no pending tasks!");
        } else {
            printlnWithWonky("Below are the list of tasks you have added!");
        }
    }

    private void printFindTitle(int size, String searchStr) throws DukeLoggerException {
        assert size >= 0 : "Size should not be negative";
        if (size == 0) {
            printlnWithWonky("No tasks containing [" + searchStr + "] found!");
        } else {
            printlnWithWonky("Below are the list of tasks containing [" + searchStr + "] found!");
        }
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to print.
     * @throws DukeLoggerException If there is an error with the logger.
     */
    public void printListCommand(List<Task> tasks) throws DukeLoggerException {
        printListTitle(tasks.size());
        if (!isLoading) {
            for (int i = 0; i < tasks.size(); i += 1) {
                task(tasks.get(i).getStatusMsg(i + 1));
            }
        }
    }

    /**
     * Prints log message for the start of the application.
     *
     * @param modeToSet
     * @throws DukeLoggerException
     */
    public void startUp(WonkyMode modeToSet) throws DukeLoggerException {
        mode = modeToSet;
        if (hasError) {
            println("");
            printlnWithWonky("Initialisation completed with warnings.");
        } else {
            printlnWithWonky("Initialisation completed successfully.");
        }
        println("");
        printlnWithWonky("Hello from" + System.lineSeparator() + LOGO);
        printlnWithWonky("I'm Wonky the Fairy." + System.lineSeparator());
        printlnWithWonky("What can I do for you?" + System.lineSeparator());
    }

    /**
     * Prints log message for the initialisation of the storage.
     *
     * @param isNew
     * @throws DukeLoggerException
     */
    public void initialiseStorage(boolean isNew) throws DukeLoggerException {
        if (isNew) {
            printlnWithWonky("Initialising...");
        } else {
            printlnWithWonky("Initialising and loading data from storage...");
            println(" ");
        }
    }

    /**
     * Prints log message for a task.
     *
     * @param task
     * @throws DukeLoggerException
     */
    private void task(String task) throws DukeLoggerException {
        println("\t" + task);
    }

    /**
     * Prints log message for a task added to the list.
     *
     * @param task
     * @param desc
     * @throws DukeLoggerException
     */
    public void addedToList(Task task, int totalTasks) throws DukeLoggerException {
        printlnWithWonky(String.format(ADD_TO_LIST_MSG, task.getLitr(), task.getDescription()));
        if (!isLoading) {
            task(task.getStatusMsg(1));
            padLine();
        }
        printlnWithWonky(String.format("You now have %d task(s) in the list.", totalTasks));
    }

    /**
     * Prints log message for the bye command.
     *
     * @throws DukeLoggerException
     */
    public void bye() throws DukeLoggerException {
        printlnWithWonky("Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
    }

    /**
     * Prints log message for an unknown command.
     *
     * @param cmd
     * @throws DukeLoggerException
     */
    public void unknownCommand(String cmd) throws DukeLoggerException {
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
    public void mismatchArgs(String cmd) throws DukeLoggerException {
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
    public void mismatchArgs(String cmd, int expectedArgCount) throws DukeLoggerException {
        mismatchArgs(cmd);
        printWarnWithWonky(String.format(EXPECTED_ARG_MSG, expectedArgCount));
    }

    /**
     * Prints log message for an expected integer argument type.
     *
     * @param val
     * @throws DukeLoggerException
     */
    public void expectedInteger(String val) throws DukeLoggerException {
        printWarnWithWonky(String.format(EXPECTED_TYPE_MSG, "Integer", val));
    }

    /**
     * Prints log message for an expected date argument type.
     *
     * @param val
     * @throws DukeLoggerException
     */
    public void expectedDateTime(String val) throws DukeLoggerException {
        printWarnWithWonky(String.format(EXPECTED_TYPE_MSG, "yyyy-MM-dd HH:mm", val));
    }

    /**
     * Prints log message to suggest a command.
     *
     * @param cmd
     * @throws DukeLoggerException
     */
    public void suggestCommand(String cmd) throws DukeLoggerException {
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
    public void alreadyMarked(String desc, String isDoneLitr) throws DukeLoggerException {
        printWarnWithWonky(String.format(ALREADY_MARK_MSG, desc, isDoneLitr));
    }

    public void taskMarked(Task task) throws DukeLoggerException {
        String isDoneLitr = task.getDone() ? "done" : "not done";
        printlnWithWonky(String.format(TASK_MARKED_MSG, task.getDescription(), isDoneLitr));
        if (!isLoading) {
            task(task.getStatusMsg(1));
        }
    }

    /**
     * Prints log message for a typo in deleting a task.
     *
     * @param idx
     * @throws DukeLoggerException
     */
    public void deleteTypo(int idx) throws DukeLoggerException {
        typo("delete", idx);
    }

    /**
     * Prints log message for a typo in marking a task.
     *
     * @param idx
     * @throws DukeLoggerException
     */
    public void markTypo(int idx) throws DukeLoggerException {
        typo("mark", idx);
    }

    /**
     * Prints log message for a typo in unmarking a task.
     *
     * @param idx
     * @throws DukeLoggerException
     */
    public void unmarkTypo(int idx) throws DukeLoggerException {
        typo("unmark", idx);
    }

    private void typo(String type, int idx) throws DukeLoggerException {
        String msg = TYPO_MSG;
        if (idx <= 0) {
            msg = INVALID_MSG;
        }
        printWarnWithWonky(String.format(msg, type, idx));
    }

    /**
     * Prints log message for deleting a task.
     *
     * @param desc
     * @throws DukeLoggerException
     */
    public void taskDeleted(String desc, int totalTasks) throws DukeLoggerException {
        printlnWithWonky(String.format(TASK_DELETED_MSG, desc));
        if (totalTasks == 0) {
            printlnWithWonky("Hooray you have no pending tasks!");
        } else {
            printlnWithWonky(String.format("You now have %d task(s) in the list.", totalTasks));
        }
    }

    /**
     * Returns isLoading.
     *
     * @param bool
     */
    public void setIsLoading(boolean bool) {
        isLoading = bool;
    }

    /**
     * Prints log message for the bye command not supposed to be in storage.
     *
     * @throws DukeLoggerException
     */
    public void byeInStorage() throws DukeLoggerException {
        printWarnWithWonky("Bye command should not be stored.");
    }

    /**
     * Returns mode.
     *
     * @return
     */
    public WonkyMode getMode() {
        return mode;
    }

    /**
     * Returns isLoading.
     *
     * @return
     */
    public boolean getLoading() {
        return isLoading;
    }

    private String randomFromArray(List<String> choices) {
        if (WonkyMode.TEST.equals(mode)) {
            return choices.get(0);
        }
        return choices.get(RND.nextInt(choices.size()));
    }

    /**
     * Prints log message for a list of tasks.
     *
     * @param foundTasks
     * @throws DukeLoggerException
     */
    public void printFoundTasks(List<Task> foundTasks, String searchStr) throws DukeLoggerException {
        printFindTitle(foundTasks.size(), searchStr);
        for (int i = 0; i < foundTasks.size(); i += 1) {
            task(foundTasks.get(i).getStatusMsg(i + 1));
        }
    }

    /**
     * Prints log message for no tasks found.
     *
     * @param searchStr
     * @throws DukeLoggerException
     */
    public void noTasksFound(String searchStr) throws DukeLoggerException {
        printFindTitle(0, searchStr);
    }

    /**
     * Prints log message for a list of stashed tasks.
     *
     * @param stashList
     * @throws DukeLoggerException
     */
    public void printStashList(List<String> stashList) throws DukeLoggerException {
        if (stashList.size() > 0) {
            printlnWithWonky("Below are the list of stashes you have!");
            for (int i = 0; i < stashList.size(); i += 1) {
                println("\t" + (i + 1) + ". " + stashList.get(i));
            }
        } else {
            printlnWithWonky("You do not have any stashes!");
        }
    }

    public void stashCleared() throws DukeLoggerException {
        printlnWithWonky("Your stashes have been cleared!");
        printlnWithWonky("You now have no stashes!");
    }

    public void invalidStashCommand(String string) throws DukeLoggerException {
        printlnWithWonky("You have entered an invalid stash command [" + string + "]!");
    }

    public void invalidStashName(String stashName) throws DukeLoggerException {
        printlnWithWonky("You have entered an invalid stash name [" + stashName + "]!");
    }

    public void stashPopped(String stashName) throws DukeLoggerException {
        printlnWithWonky("I have popped this stash [" + stashName + "]!");
        int stashSize = WonkyStorage.getInstance().getStashList().size();
        if (stashSize == 0) {
            printlnWithWonky("You have no more stashes!");
        } else {
            printlnWithWonky("You now have " + stashSize + " stash(es)!");
        }
    }

    public void stashApplied(String stashName) throws DukeLoggerException {
        printlnWithWonky("I have applied this stash [" + stashName + "]!");
    }

    public void stashAdded(String stashName) throws DukeLoggerException {
        printlnWithWonky("I have added [" + stashName + "] to your stashes!");
        printlnWithWonky("You now have " + (WonkyStorage.getInstance().getStashList().size()) + " stash(es)!");
    }

    public void printHelpCommand() throws DukeLoggerException {
        printlnWithWonky(HELP_TEXT);
    }

    /**
     * Flushes all the responses.
     *
     * @return returns the flushed response.
     */
    public String flushResponse() {
        String toFlush = response;
        response = "";
        return toFlush;
    }

    public void invalidTaskIdx(int i) throws DukeLoggerException {
        printWarnWithWonky(String.format(TASK_NOT_EXIST_MSG, i));
    }
}
