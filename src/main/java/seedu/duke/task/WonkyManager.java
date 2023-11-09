package seedu.duke.task;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.commands.types.ByeCommand;
import seedu.duke.commands.types.DeadlineCommand;
import seedu.duke.commands.types.DeleteCommand;
import seedu.duke.commands.types.EventCommand;
import seedu.duke.commands.types.FindCommand;
import seedu.duke.commands.types.HelpCommand;
import seedu.duke.commands.types.ListCommand;
import seedu.duke.commands.types.MarkCommand;
import seedu.duke.commands.types.StashCommand;
import seedu.duke.commands.types.TodoCommand;
import seedu.duke.commands.types.UnmarkCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.exceptions.DukeManagerException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyStorage;

/**
 * Manages the tasks in the Duke application.
 * It contains methods for executing commands, modifying tasks, and parsing commands into tasks.
 */
public class WonkyManager {
    // Lists for storing command arguments and tasks.
    private List<CommandType> cmdTypes = new ArrayList<CommandType>();
    private List<Task> tasks = new ArrayList<Task>();

    private static WonkyManager wonkyManager;
    private WonkyLogger wonkyLogger;
    private WonkyStorage wonkyStorage;

    public WonkyManager () {}

    public static WonkyManager getInstance() {
        if (wonkyManager == null) {
            wonkyManager = new WonkyManager();
        }
        return wonkyManager;
    }

    public void setReferences(WonkyLogger wonkyLogger, WonkyStorage wonkyStorage) {
        this.wonkyLogger = wonkyLogger;
        this.wonkyStorage = wonkyStorage;
    }

    /**
     * Checks and executes the given command argument.
     *
     * @param cmdType the command argument to execute.
     * @throws DukeException if there is an error executing the command.
     */
    public void checkCommand(CommandEnum cmdStr, String argumentStr) throws DukeException {
        CommandType command;
        switch (cmdStr) {
        case BYE:
            command = new ByeCommand(cmdStr, argumentStr);
            break;
        case LIST:
            command = new ListCommand(cmdStr, argumentStr);
            break;
        case HELP:
            command = new HelpCommand(cmdStr, argumentStr);
            break;
        case FIND:
            command = new FindCommand(cmdStr, argumentStr);
            break;
        case STASH:
            command = new StashCommand(cmdStr, argumentStr);
            break;
        case DELETE:
            command = new DeleteCommand(cmdStr, argumentStr);
            break;
        case MARK:
            command = new MarkCommand(cmdStr, argumentStr);
            break;
        case UNMARK:
            command = new UnmarkCommand(cmdStr, argumentStr);
            break;
        case TODO:
            command = new TodoCommand(cmdStr, argumentStr);
            break;
        case DEADLINE:
            command = new DeadlineCommand(cmdStr, argumentStr);
            break;
        case EVENT:
            command = new EventCommand(cmdStr, argumentStr);
            break;
        default:
            throw new DukeManagerException("Unhandled command to be added: " + cmdStr.getLitr());
        }
        command.execute();
    }

    /**
     * Adds the given task to the task list.
     *
     * @param toAdd the task to add.
     * @throws DukeException if there is an error adding the task.
     */
    public void addTask(Task toAdd) throws DukeException {
        tasks.add(toAdd);
        wonkyLogger.addedToList(toAdd, tasks.size());
    }

    public boolean isValidTaskIdx(int taskIdx) throws DukeLoggerException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            return true;
        } else {
            wonkyLogger.invalidTaskIdx(taskIdx + 1);
            return false;
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<CommandType> getCmdTypes() {
        return cmdTypes;
    }

    public void addCmdType(CommandType cmdType) throws DukeException {
        cmdTypes.add(cmdType);
        wonkyStorage.save(cmdTypes);
    }

    public void resetCmdTypes() throws DukeException {
        cmdTypes = new ArrayList<CommandType>();
    }
}
