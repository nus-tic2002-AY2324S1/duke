package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandArgument;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.exceptions.DukeManagerException;
import seedu.duke.helper.WonkyDateTime;
import seedu.duke.helper.utils.WonkyUtils;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;
import seedu.duke.io.WonkyStorage;

/**
 * Manages the tasks in the Duke application.
 * It contains methods for executing commands, modifying tasks, and parsing commands into tasks.
 */
public class WonkyManager {

    // Constants for command argument validation.
    private final String EMPTY_LITR = "";
    private final int ZERO_ARGS = 0;
    private final int ONE_ARGS = 1;
    private final int TWO_ARGS = 2;

    private final int TODO_ARGS = 1;
    private final int DEADLINE_ARGS = 2;
    private final int EVENT_ARGS = 3;

    // Constants for command argument indices.
    private final int DESC_IDX = 0;
    private final int BY_IDX = 1;
    private final int FROM_IDX = 1;
    private final int TO_IDX = 2;

    private final int ZERO_IDX = 0;
    private final int ONE_IDX = 1;

    // Lists for storing command arguments and tasks.
    private List<CommandArgument> cmdArgs = new ArrayList<CommandArgument>();
    private List<Task> tasks = new ArrayList<Task>();

    private static WonkyManager wonkyManager;
    private WonkyScanner wonkyScanner;
    private WonkyLogger wonkyLogger;
    private WonkyStorage wonkyStorage;

    public WonkyManager () {}

    public static WonkyManager getInstance() {
        if (wonkyManager == null) {
            wonkyManager = new WonkyManager();
        }
        return wonkyManager;
    }

    public void setReferences(WonkyLogger wonkyLogger, WonkyStorage wonkyStorage, WonkyScanner wonkyScanner) {
        this.wonkyLogger = wonkyLogger;
        this.wonkyStorage = wonkyStorage;
        this.wonkyScanner = wonkyScanner;
    }

    /**
     * Executes the given command argument.
     *
     * @param cmdArg the command argument to execute.
     * @throws DukeException if there is an error executing the command.
     */
    public void executeCommand(CommandArgument cmdArg) throws DukeException {
        switch (cmdArg.getCmd()) {
        case BYE:
            if (validateArgs(cmdArg, ZERO_ARGS)) {
                wonkyScanner.bye();
            }
            break;
        case LIST:
            if (validateArgs(cmdArg, ZERO_ARGS)) {
                wonkyLogger.printListCommand(tasks);
            }
            break;
        case HELP:
            if (validateArgs(cmdArg, ZERO_ARGS)) {
                wonkyLogger.printHelpCommand();
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

    private void validateAndExecuteStash(CommandArgument cmdArg) throws DukeException {
        List<String> cmdList = cmdArg.getArgList();
        List<String> stashList = wonkyStorage.getStashList();
        if (validateArgs(cmdArg, ONE_ARGS, false)) {
            String firstArg = cmdList.get(ZERO_IDX).trim();
            if (firstArg.equals("list")) {
                wonkyLogger.printStashList(stashList);
            } else if (firstArg.equals("clear")) {
                wonkyStorage.clearStash();
                wonkyLogger.stashCleared();
            } else {
                wonkyLogger.invalidStashCommand(firstArg);
            }
        } else if (validateArgs(cmdArg, TWO_ARGS, false)) {
            String firstArg = cmdList.get(ZERO_IDX).trim();
            String stashName = cmdList.get(ONE_IDX).trim();
            if (firstArg.equals("add")) {
                wonkyStorage.addStash(stashName, cmdArgs);
                wonkyLogger.stashAdded(stashName);
            } else if (!stashList.contains(stashName)) {
                wonkyLogger.invalidStashName(stashName);
            } else if (firstArg.equals("pop")) {
                wonkyStorage.popStash(stashName);
                wonkyLogger.stashPopped(stashName);
            } else {
                wonkyLogger.invalidStashCommand(firstArg);
            }
        }
    }

    /**
     * Finds tasks that contain the given string in their description.
     *
     * @param cmdArg the command argument specifying the string to find.
     * @throws DukeLoggerException
     */
    private void findTasks(CommandArgument cmdArg) throws DukeLoggerException {
        List<Task> foundTasks = new ArrayList<Task>();
        String searchStr = cmdArg.getArgList().get(0);
        for (Task task : tasks) {
            if (task.description.contains(searchStr)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() > 0) {
            wonkyLogger.printFoundTasks(foundTasks, searchStr);
        } else {
            wonkyLogger.noTasksFound(searchStr);
        }
    }

    /**
     * Modifies the task specified in the given command argument.
     *
     * @param cmdArg the command argument specifying the task to modify.
     * @throws DukeException if there is an error modifying the task.
     */
    public void modifyTask(CommandArgument cmdArg) throws DukeException {
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
                        wonkyLogger.taskDeleted(taskToDelete.description);
                    } catch (IndexOutOfBoundsException e) {
                        wonkyLogger.deleteTypo(taskIdx + 1);
                        break;
                    }
                    break;
                default:
                    throw new DukeManagerException("Unhandled modify task operation: " + cmdArg.getCmdLitr());
                }
            }
        } catch (NumberFormatException e) {
            wonkyLogger.expectedInteger(argList.get(0));
        }
    }

    /**
     * Parses the given command argument into a task and adds it to the task list.
     *
     * @param cmdArg the command argument to parse.
     * @throws DukeException if there is an error parsing the command or adding the task.
     */
    private void parseCmdToTask(CommandArgument cmdArg) throws DukeException {
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
    private void addTask(Task toAdd) throws DukeException {
        tasks.add(toAdd);
        wonkyLogger.addedToList(toAdd.command.getLitr(), toAdd.description);
    }

    private boolean isValidTaskIdx(int taskIdx) throws DukeLoggerException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            return true;
        } else {
            wonkyLogger.invalidTaskIdx(taskIdx + 1);
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
    private boolean validateArgs(CommandArgument cmdArg, int expectedSize) throws DukeException {
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
    private boolean validateArgs(
        CommandArgument cmdArg, int expectedSize, boolean isError
    ) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        int argCount = cmdArg.getArgCount();
        if (wonkyLogger.getLoading()) {
            cmdArgs.add(cmdArg);
            wonkyStorage.save(cmdArgs);
        }
        if (argCount == expectedSize && expectedSize == ZERO_ARGS) {
            cmdArgs.add(cmdArg);
            wonkyStorage.save(cmdArgs);
            return true;
        }
        if (argCount != expectedSize || (argList.get(0).trim().isEmpty())) {
            if (isError) {
                wonkyLogger.mismatchArgs(cmdArg.getCmdLitr(), expectedSize);
            }
            return false;
        }
        for (String arg : argList) {
            if (EMPTY_LITR.equals(arg)) {
                return false;
            }
        }
        if (CommandEnum.EVENT.equals(cmdArg.getCmd()) || CommandEnum.DEADLINE.equals(cmdArg.getCmd())) {
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
        if (!wonkyLogger.getLoading()) {
            // Save the command argument to storage if it is not being loaded from storage
            cmdArgs.add(cmdArg);
            wonkyStorage.save(cmdArgs);
        }
        return true;
    }

    /**
     * Checks if a given string is a valid date and time in the format specified by {@link WonkyDateTime#getDtf()}.
     * If the string is not a valid date and time, it checks if it is a valid date in the format specified by
     * {@link WonkyDateTime#getMappedDateTimeStr(String)}.
     * If the string is not a valid date or date and time,
     * it logs an error message using {@link wonkyLogger#expectedDateTime(String)}.
     *
     * @param str the string to be checked.
     * @return true if the string is a valid date or date and time, false otherwise.
     * @throws DukeException if there is an error parsing the string as a date or date and time.
     */
    private boolean isValidDateTime(String str) throws DukeException {
        str = str.trim();
        try {
            LocalDateTime.parse(str, WonkyDateTime.dtf);
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(new WonkyDateTime(str))) {
                return true;
            }
            wonkyLogger.expectedDateTime(str);
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
    private WonkyDateTime parseToDate(String str) throws DukeException {
        str = str.trim();
        try {
            return new WonkyDateTime(LocalDateTime.parse(str, WonkyDateTime.dtf));
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(WonkyUtils.isValidNaturalDateTimeStr(str))) {
                return new WonkyDateTime(str);
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
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Resets the task list by creating a new empty ArrayList of tasks.
     */
    public void resetTaskList() {
        tasks = new ArrayList<Task>();
    }

    public void resetCmdArgs() {
        cmdArgs = new ArrayList<CommandArgument>();
    }
}
