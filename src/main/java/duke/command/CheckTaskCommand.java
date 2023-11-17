package duke.command;

import java.util.List;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents a command for checking tasks on a specific date.
 */
abstract class CheckTaskCommand implements Command {

    /**
     * Executes the command to check tasks without specifying a date.
     *
     * @param display  The message display interface to show messages to the user.
     * @param taskList The list of tasks to check.
     */
    public abstract void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList);

}
