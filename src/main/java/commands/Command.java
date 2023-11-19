package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents a command to be executed by the Joshua chatbot.
 * Other command classes should extend this class and implement the execute method.
 */
public class Command {
    protected boolean isExit = false;

    protected Command() {
    }

    /**
     * Performs the operations of the command.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object for handling task storage.
     * @throws Exception Any exception that might occur during command execution.
     */
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) throws Exception {
        return;
    }

    /**
     * @return isExit boolean value.
     */
    public boolean isExit() {
        return isExit;
    }
}
