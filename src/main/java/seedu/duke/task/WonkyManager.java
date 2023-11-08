package seedu.duke.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.commands.WonkyDateTime;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.exceptions.DukeManagerException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;
import seedu.duke.io.WonkyStorage;

/**
 * Manages the tasks in the Duke application.
 * It contains methods for executing commands, modifying tasks, and parsing commands into tasks.
 */
public class WonkyManager {

    // Constants for command argument validation.
    private static final String EMPTY_LITR = "";
    private static final int ZERO_ARGS = 0;
    private static final int ONE_ARGS = 1;
    private static final int TWO_ARGS = 2;

    private static final int TODO_ARGS = 1;
    private static final int DEADLINE_ARGS = 2;
    private static final int EVENT_ARGS = 3;

    // Constants for command argument indices.
    private static final int DESC_IDX = 0;
    private static final int BY_IDX = 1;
    private static final int FROM_IDX = 1;
    private static final int TO_IDX = 2;

    private static final int ZERO_IDX = 0;
    private static final int ONE_IDX = 1;

    // Lists for storing command arguments and tasks.
    private static List<CommandArgument> cmdArgs = new ArrayList<CommandArgument>();
    private static List<Task> tasks = new ArrayList<Task>();

    /**
     * Executes the given command argument.
     *
     * @param cmdArg the command argument to execute.
     * @throws DukeException if there is an error executing the command.
     * @throws IOException
     */
    public static void executeCommand(CommandArgument cmdArg) throws DukeException {
        switch (cmdArg.getCmd()) {
        case BYE:
            if (validateArgs(cmdArg, ZERO_ARGS)) {
                WonkyScanner.bye();
            }
            break;
        case LIST:
            if (validateArgs(cmdArg, ZERO_ARGS)) {
                WonkyLogger.printListCommand(tasks);
            }
            break;
        case HELP:
            if (validateArgs(cmdArg, ZERO_ARGS)) {
                WonkyLogger.printHelpCommand();
            }
            break;
        case FIND:
            if (validateArgs(cmdArg, ONE_ARGS)) {
                findTasks(cmdArg);
            }
            break;
        case STASH:
            validateAndExecuteStash(cmdArg);
            break;
        case DELETE:
            //Fallthrough
        case MARK:
            //Fallthrough
        case UNMARK:
            modifyTask(cmdArg);
            break;
        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            parseCmdToTask(cmdArg);
            break;
        default:
            throw new DukeManagerException("Unhandled command to be added: " + cmdArg.getCmdLitr());
        }
    }

    private static void validateAndExecuteStash(CommandArgument cmdArg) throws DukeException {
        List<String> cmdList = cmdArg.getArgList();
        List<String> stashList = WonkyStorage.getStashList();
        if (validateArgs(cmdArg, ONE_ARGS, false)) {
            String firstArg = cmdList.get(ZERO_IDX).trim();
            if (firstArg.equals("list")) {
                WonkyLogger.printStashList(stashList);
            } else if (firstArg.equals("clear")) {
                WonkyStorage.clearStash();
                WonkyLogger.stashCleared();
            } else {
                WonkyLogger.invalidStashCommand(firstArg);
            }
        } else if (validateArgs(cmdArg, TWO_ARGS, false)) {
            String firstArg = cmdList.get(ZERO_IDX).trim();
            String stashName = cmdList.get(ONE_IDX).trim();
            if (firstArg.equals("add")) {
                WonkyStorage.addStash(stashName, cmdArgs);
                WonkyLogger.stashAdded(stashName);
            } else if (!stashList.contains(stashName)) {
                WonkyLogger.invalidStashName(stashName);
            } else if (firstArg.equals("pop")) {
                WonkyStorage.popStash(stashName);
                WonkyLogger.stashPopped(stashName);
            } else {
                WonkyLogger.invalidStashCommand(firstArg);
            }
        }
    }

    /**
     * Finds tasks that contain the given string in their description.
     *
     * @param cmdArg the command argument specifying the string to find.
     * @throws DukeLoggerException
     */
    private static void findTasks(CommandArgument cmdArg) throws DukeLoggerException {
        List<Task> foundTasks = new ArrayList<Task>();
        String searchStr = cmdArg.getArgList().get(0);
        for (Task task : tasks) {
            if (task.description.contains(searchStr)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() > 0) {
            WonkyLogger.printFoundTasks(foundTasks, searchStr);
        } else {
            WonkyLogger.noTasksFound(searchStr);
        }
    }

    /**
     * Modifies the task specified in the given command argument.
     *
     * @param cmdArg the command argument specifying the task to modify.
     * @throws DukeException if there is an error modifying the task.
     */
    public static void modifyTask(CommandArgument cmdArg) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        try {
            int taskIdx = Integer.parseInt(argList.get(0)) - 1;
            assert taskIdx >= 0 : "taskIdx cannot be negative!";
            assert taskIdx < tasks.size() : "taskIdx cannot be larger than tasks size!";
            if (isValidTaskIdx(taskIdx) && validateArgs(cmdArg, ONE_ARGS)) {
                switch (cmdArg.getCmd()) {
                case MARK:
                    tasks.get(taskIdx).setDone(true);
                    break;
                case UNMARK:
                    tasks.get(taskIdx).setDone(false);
                    break;
                case DELETE:
                    try {
                        Task taskToDelete = tasks.get(taskIdx);
                        tasks.remove(taskIdx);
                        WonkyLogger.taskDeleted(taskToDelete.description);
                    } catch (IndexOutOfBoundsException e) {
                        WonkyLogger.deleteTypo(taskIdx + 1);
                        break;
                    }
                    break;
                default:
                    throw new DukeManagerException("Unhandled modify task operation: " + cmdArg.getCmdLitr());
                }
            }
        } catch (NumberFormatException e) {
            WonkyLogger.expectedInteger(argList.get(0));
        }
    }

    /**
     * Parses the given command argument into a task and adds it to the task list.
     *
     * @param cmdArg the command argument to parse.
     * @throws DukeException if there is an error parsing the command or adding the task.
     */
    private static void parseCmdToTask(CommandArgument cmdArg) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        switch (cmdArg.getCmd()) {
        case TODO:
            if (validateArgs(cmdArg, TODO_ARGS)) {
                addTask(new Todo(argList.get(DESC_IDX)));
            }
            break;
        case DEADLINE:
            if (validateArgs(cmdArg, DEADLINE_ARGS)) {
                addTask(
                    new Deadline(
                        argList.get(DESC_IDX),
                        parseToDate(argList.get(BY_IDX).trim())
                    )
                );
            }
            break;
        case EVENT:
            if (validateArgs(cmdArg, EVENT_ARGS)) {
                addTask(
                    new Event(
                        argList.get(DESC_IDX),
                        parseToDate(argList.get(FROM_IDX).trim()),
                        parseToDate(argList.get(TO_IDX).trim())
                    )
                );
            }
            break;
        default:
            throw new DukeManagerException("Unhandled task to be added: " + cmdArg.getCmdLitr());
        }

    }

    /**
     * Adds the given task to the task list.
     *
     * @param toAdd the task to add.
     * @throws DukeException if there is an error adding the task.
     */
    private static void addTask(Task toAdd) throws DukeException {
        tasks.add(toAdd);
        WonkyLogger.addedToList(toAdd.command.getLitr(), toAdd.description);
    }

    private static boolean isValidTaskIdx(int taskIdx) throws DukeLoggerException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            return true;
        } else {
            WonkyLogger.invalidTaskIdx(taskIdx + 1);
            return false;
        }
    }

    /**
     * Validates that the given command argument has the expected number of arguments.
     *
     * @param cmdArg the command argument to validate.
     * @param expectedSize the expected number of arguments.
     * @return true if the command argument is valid, false otherwise.
     * @throws DukeException if there is an error validating the command argument.
     */
    private static boolean validateArgs(CommandArgument cmdArg, int expectedSize) throws DukeException {
        return validateArgs(cmdArg, expectedSize, true);
    }

    /**
     * Validates that the given command argument has the expected number of arguments.
     *
     * @param cmdArg the command argument to validate.
     * @param expectedSize the expected number of arguments.
     * @return true if the command argument is valid, false otherwise.
     * @throws DukeException if there is an error validating the command argument.
     */
    private static boolean validateArgs(
        CommandArgument cmdArg, int expectedSize, boolean isError
    ) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        int argCount = cmdArg.getArgCount();
        if (WonkyLogger.getLoading()) {
            cmdArgs.add(cmdArg);
            WonkyStorage.save(cmdArgs);
        }
        if (argCount == expectedSize && expectedSize == ZERO_ARGS) {
            cmdArgs.add(cmdArg);
            WonkyStorage.save(cmdArgs);
            return true;
        }
        if (argCount != expectedSize || (argList.get(0).trim().isEmpty())) {
            if (isError) {
                WonkyLogger.mismatchArgs(cmdArg.getCmdLitr(), expectedSize);
            }
            return false;
        }
        for (String arg : argList) {
            if (EMPTY_LITR.equals(arg)) {
                return false;
            }
        }
        if (Command.EVENT.equals(cmdArg.getCmd()) || Command.DEADLINE.equals(cmdArg.getCmd())) {
            String newCmdArgStr = cmdArg.getArgList().get(0);
            for (int i = 1; i < cmdArg.getArgCount(); i += 1) {
                String currArg = cmdArg.getArgList().get(i);
                if (!isValidDateTime(currArg)) {
                    return false;
                } else {
                    newCmdArgStr += "|" + parseToDate(currArg).getDateTimeStr();
                }
            }
            cmdArg.setArg(newCmdArgStr);
        }
        if (!WonkyLogger.getLoading()) {
            // Save the command argument to storage if it is not being loaded from storage
            cmdArgs.add(cmdArg);
            WonkyStorage.save(cmdArgs);
        }
        return true;
    }

    /**
     * Returns the last command argument in the command argument list.
     *
     * @return the last command argument in the list.
     */
    private static Command getLastCmd() {
        return cmdArgs.get(cmdArgs.size() - 1).getCmd();
    }

    /**
     * Checks if a given string is a valid date and time in the format specified by {@link WonkyDateTime#getDtf()}.
     * If the string is not a valid date and time, it checks if it is a valid date in the format specified by
     * {@link WonkyDateTime#getMappedDateTimeStr(String)}.
     * If the string is not a valid date or date and time,
     * it logs an error message using {@link WonkyLogger#expectedDateTime(String)}.
     *
     * @param str the string to be checked.
     * @return true if the string is a valid date or date and time, false otherwise.
     * @throws DukeException if there is an error parsing the string as a date or date and time.
     */
    private static boolean isValidDateTime(String str) throws DukeException {
        str = str.trim();
        try {
            LocalDateTime.parse(str, WonkyDateTime.getDtf());
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(WonkyDateTime.getMappedDateTimeStr(str))) {
                return true;
            }
            WonkyLogger.expectedDateTime(str);
            return false;
        }
        return true;
    }

    /**
     * Parses a string to a WonkyDateTime object.
     *
     * @param str the string to be parsed.
     * @return a WonkyDateTime object representing the parsed date and time.
     * @throws DukeException if the string cannot be parsed into a valid date and time.
     */
    private static WonkyDateTime parseToDate(String str) throws DukeException {
        str = str.trim();
        try {
            return new WonkyDateTime(LocalDateTime.parse(str, WonkyDateTime.getDtf()));
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(WonkyDateTime.getMappedDateTimeStr(str))) {
                return new WonkyDateTime(WonkyDateTime.getMappedDateTimeStr(str));
            }
            throw new DukeManagerException("Invalid date value.");
        } catch (Exception e) {
            throw new DukeManagerException(e);
        }
    }

    /**
     * Returns a list of all tasks.
     *
     * @return a list of all tasks.
     */
    public static List<Task> getTasks() {
        return tasks;
    }

    /**
     * Resets the task list by creating a new empty ArrayList of tasks.
     */
    public static void resetTaskList() {
        tasks = new ArrayList<Task>();
    }

    public static void resetCmdArgs() {
        cmdArgs = new ArrayList<CommandArgument>();
    }
}
