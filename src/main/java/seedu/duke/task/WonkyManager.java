package seedu.duke.task;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
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

    private static List<CommandArgument> cmdArgs = new ArrayList<CommandArgument>();
    private static List<Task> tasks = new ArrayList<Task>();

    public static void executeCommand(CommandArgument cmdArg) throws DukeException {
        cmdArgs.add(cmdArg);
        switch (cmdArg.getCmd()) {
        case BYE:
            WonkyScanner.bye();
            break;
        case LIST:
            WonkyLogger.printListCommand(tasks);
            break;
        case MARK:
            //Fallthrough
        case UNMARK:
            mark(cmdArg);
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

    private static void mark(CommandArgument cmdArg) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        if (validateArgs(argList , 1)) {
            try {
                int taskToMark = Integer.parseInt(argList.get(0)) - 1;
                switch (cmdArg.getCmd()) {
                case MARK:
                    tasks.get(taskToMark).setDone(true);
                    break;
                case UNMARK:
                    tasks.get(taskToMark).setDone(false);
                    break;
                default:
                    throw new DukeManagerException("Unhandled mark operation: " + cmdArg.getCmdLitr());
                }
            } catch (NumberFormatException e) {
                WonkyLogger.expectedInteger(argList.get(0));
            }
        }
    }

    private static void parseCmdToTask(CommandArgument cmdArg) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        switch (cmdArg.getCmd()) {
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
            throw new DukeManagerException("Unhandled task to be added: " + cmdArg.getCmdLitr());
        }

    }

    private static void addTask(Task toAdd) throws DukeException {
        tasks.add(toAdd);
        WonkyLogger.addedToList(toAdd.command.getLitr(), toAdd.description);
    }

    private static boolean validateArgs(List<String> argList, int expectedSize) throws DukeException {
        if (argList.size() != expectedSize || argList.get(0).trim().isEmpty()) {
            WonkyLogger.mismatchArgs(getLastCmd().getLitr(), expectedSize);
            return false;
        }
        for (String arg : argList) {
            if (EMPTY_LITR.equals(arg)) {
                return false;
            }
        }
        return true;
    }

    private static Command getLastCmd() {
        return cmdArgs.get(cmdArgs.size() - 1).getCmd();
    }
}