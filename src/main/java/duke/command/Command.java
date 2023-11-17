package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * An abstract class representing a command that can be executed.
 */
public abstract class Command {
    /**
     * Execute the command with access to the tasklist, UI, and storage.
     *
     * @param taskList The task list to operate on.
     * @param ui       The user interface for input and output.
     * @param storage  The storage to read and write data.
     * @throws DukeException If an error occurs during command execution.
     * @throws IOException   If an IO error occurs during storage operation.
     */
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException;

    /**
     * Indicates the command should exit the application
     *
     * @return true if the command should exit , false otherwise
     */
    public abstract boolean isExit();

    public abstract boolean isChangingState();
}
