package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * The base class for command execution.
 *
 * Stores user input and provides a mechanism to check if the user
 * wishes to exit the program. Subclasses are expected to provide their
 * own implementation of the {@code execute} method to define specific
 * command behavior.
 * </p>
 */

public abstract class Command {
    protected boolean isExit = false;
    
    /**
     * Executes the specified user command, defining its behavior.
     * <p>
     * This method is responsible for handling a user command and performing
     * the necessary actions accordingly.
     *
     * @param tasks   The current TaskList of the user.
     * @param ui      The UI object responsible for printing statements.
     * @param storage The storage object used for saving and storing user input.
     */
    abstract public void execute(TaskList tasks, UI ui, Storage storage);

    public Boolean isExit() {
        return this.isExit;
    }
}
