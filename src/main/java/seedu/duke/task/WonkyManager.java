package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.commands.WonkyDateTime;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeManagerException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;
import seedu.duke.io.WonkyStorage;

public class WonkyManager {

    private final static String EMPTY_LITR = "";

    private final static int ZERO_ARGS = 0;
    private final static int ONE_ARGS = 1;

    private final static int TODO_ARGS = 1;
    private final static int DEADLINE_ARGS = 2;
    private final static int EVENT_ARGS = 3;

    private final static int DESC_IDX = 0;
    private final static int BY_IDX = 1;
    private final static int FROM_IDX = 1;
    private final static int TO_IDX = 2;

    private static List<CommandArgument> cmdArgs = new ArrayList<CommandArgument>();
    private static List<Task> tasks = new ArrayList<Task>();

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
        case DELETE:
            //Fallthrough
        case MARK:
            //Fallthrough
        case UNMARK:
            if (validateArgs(cmdArg, ONE_ARGS)) {
                modifyTask(cmdArg);
            }
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

    private static void modifyTask(CommandArgument cmdArg) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        try {
            int taskIdx = Integer.parseInt(argList.get(0)) - 1;
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
        } catch (NumberFormatException e) {
            WonkyLogger.expectedInteger(argList.get(0));
        }
    }

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
                System.out.println("printing");
                System.out.println(argList.get(BY_IDX));
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

    private static void addTask(Task toAdd) throws DukeException {
        tasks.add(toAdd);
        WonkyLogger.addedToList(toAdd.command.getLitr(), toAdd.description);
    }

    private static boolean validateArgs(CommandArgument cmdArg, int expectedSize) throws DukeException {
        List<String> argList = cmdArg.getArgList();
        int argCount = cmdArg.getArgCount();
        if(WonkyLogger.isLoading) {
            cmdArgs.add(cmdArg);
            WonkyStorage.save(cmdArgs);
        }
        if (argCount == expectedSize && expectedSize == ZERO_ARGS) {
            cmdArgs.add(cmdArg);
            WonkyStorage.save(cmdArgs);
            return true;
        }
        if (argCount != expectedSize || (argList.get(0).trim().isEmpty())) {
            WonkyLogger.mismatchArgs(getLastCmd().getLitr(), expectedSize);
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
                if (!isValidDateOrDateTime(currArg)) {
                    return false;
                } else {
                    newCmdArgStr += "|" + parseToDate(currArg).getDateTimeStr();
                }
            }
            cmdArg.setArg(newCmdArgStr);
        }
        if (!WonkyLogger.isLoading) {
            cmdArgs.add(cmdArg);
            WonkyStorage.save(cmdArgs);
        }
        return true;
    }

    private static boolean isValidDateOrDateTime(String str) throws DukeException {
        str = str.trim();
        try {
            LocalDateTime.parse(str, WonkyDateTime.getDtf());
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(WonkyDateTime.getMappedDateTimeStr(str))) {
                return true;
            }
            WonkyLogger.expectedDateOrDateTime(str);
            return false;
        }
        return true;
    }

    private static WonkyDateTime parseToDate(String str) throws DukeException {
        str = str.trim();
        try {
            return new WonkyDateTime(LocalDateTime.parse(str, WonkyDateTime.getDtf()));
        } catch (DateTimeParseException e) {
            System.out.println((str));
            if (Objects.nonNull(WonkyDateTime.getMappedDateTimeStr(str))) {
                return new WonkyDateTime(WonkyDateTime.getMappedDateTimeStr(str));
            }
            throw new DukeManagerException("Invalid date value.");
        } catch (Exception e) {
            throw new DukeManagerException(e.getMessage());
        }
    }

    private static Command getLastCmd() {
        return cmdArgs.get(cmdArgs.size() - 1).getCmd();
    }
}