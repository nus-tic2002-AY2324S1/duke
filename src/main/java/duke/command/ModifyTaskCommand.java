package duke.command;

import java.util.List;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents an abstract class for commands that modify tasks in the Duke application.
 */
abstract class ModifyTaskCommand extends Command {

    // The index of the task to be modified.
    protected final int itemIndex;

    /**
     * Constructs a `ModifyTaskCommand` with the specified item index.
     *
     * @param itemIndex The index of the task to be modified.
     */
    public ModifyTaskCommand(int itemIndex) {

        this.itemIndex = itemIndex;
    }

    /**
     * Executes the command to modify a task. This method should be overridden in subclasses.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param display     The message display interface to show messages to the user.
     * @param taskList    The list of tasks that the command will operate on.
     */
    public abstract void execute(FileStorage fileStorage, MessageDisplay display,
                                 List<Task> taskList);

}
