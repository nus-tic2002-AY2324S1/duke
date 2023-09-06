package seedu.duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeManagerException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;

public class WonkyManager {

    private final static String EMPTY_LITR = "";

    private final static int TODO_ARGS = 1;
    private final static int DEADLINE_ARGS = 2;
    private final static int EVENT_ARGS = 3;

    private final static int DESC_IDX = 0;
    private final static int BY_IDX = 1;
    private final static int FROM_IDX = 1;
    private final static int TO_IDX = 0;

    private static List<Command> commands = new ArrayList<Command>();
    private static List<Task> tasks = new ArrayList<Task>();
    private static Command cmdToExecute = null;

    public static void manage() throws DukeException {
        if (Objects.nonNull(cmdToExecute)) {
            handleCmdExecution(cmdToExecute);
        }
    }

    public static void executeCommand(Command cmd, String args) throws DukeException {
        commands.add(cmd);
        switch (cmd) {
        case BYE:
            //Fallthrough
        case LIST:
            cmdToExecute = cmd;
            break;
        case MARK:
            //Fallthrough
        case UNMARK:
            mark(cmd, args);
            break;
        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            parseCmdToTask(cmd, args);
            break;
        default:
            throw new DukeManagerException("Unhandled command to be added: " + cmd.getLitr());
        }
    }

    private static void handleCmdExecution(Command cmd) throws DukeException {
            switch (cmdToExecute) {
            case BYE:
                WonkyScanner.setBye(true);
                break;
            case LIST:
                WonkyLogger.printListCommand(tasks);
                break;
            case MARK:
                break;
            case UNMARK:
                break;
            default:
                throw new DukeManagerException("Unhandled command to be executed: " + cmd.getLitr());
            }
            cmdToExecute = null;
    }

    private static void mark(Command cmd, String args) throws DukeException {
        List<String> argList = Arrays.asList(args.split("/"));
        if (validateArgs(argList , 1)) {
            try {
                int taskToMark = Integer.parseInt(argList.get(0)) - 1;
                switch (cmd) {
                case MARK:
                    tasks.get(taskToMark).setDone(true);
                    break;
                case UNMARK:
                    tasks.get(taskToMark).setDone(false);
                    break;
                default:
                    throw new DukeManagerException("Unhandled mark operation: " + cmd.getLitr());
                }
            } catch (NumberFormatException e) {
                WonkyLogger.expectedInteger(argList.get(0));
            }
        }
    }

    private static void parseCmdToTask(Command cmd, String args) throws DukeException {
        List<String> argList = Arrays.asList(args.split("/"));
        switch (cmd) {
        case TODO:
            if (validateArgs(argList, TODO_ARGS)) {
                addTask(new Todo(argList.get(DESC_IDX)));
            }
            break;
        case DEADLINE:
            if (validateArgs(argList, DEADLINE_ARGS)) {
                addTask(
                    new Deadline(
                        argList.get(DESC_IDX),
                        argList.get(BY_IDX)
                    )
                );
            }
            break;
        case EVENT:
            if (validateArgs(argList, EVENT_ARGS)) {
                addTask(
                    new Event(
                        argList.get(DESC_IDX),
                        argList.get(FROM_IDX),
                        argList.get(TO_IDX)
                    )
                );
            }
            break;
        default:
            throw new DukeManagerException("Unhandled task to be added: " + cmd.getLitr());
        }

    }

    private static void addTask(Task toAdd) throws DukeException {
        tasks.add(toAdd);
        WonkyLogger.addedToList(toAdd.command.getLitr(), toAdd.description);
    }

    private static boolean validateArgs(List<String> argList, int expectedSize) throws DukeException {
        System.out.println(argList.size());
        System.out.println("a");
        if (argList.size() != expectedSize) {
            WonkyLogger.mismatchArgs(getLastCommand().getLitr(), expectedSize);
            return false;
        }
        System.out.println("b");
        for (String arg : argList) {
            if (EMPTY_LITR.equals(arg)) {
                return false;
            }
        }
        return true;
    }

    private static Command getLastCommand() {
        return commands.get(commands.size() - 1);
    }
}