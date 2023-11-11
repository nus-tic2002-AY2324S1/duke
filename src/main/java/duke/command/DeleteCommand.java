package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * Represent a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing the task from the task list and displaying a message.
     *
     * @param taskList The task list to operate on.
     * @param ui       The user interface for input and output.
     * @param storage  The storage to read and write data.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task deletedTask = taskList.deleteTask(index);
            UI.showTaskDeleted(deletedTask, taskList);
            Storage.save(taskList);
        } catch (DukeException | IOException e) {
            UI.showError(e.getMessage());
        }
    }

    /**
     * Indicates if this command should exit the application.
     *
     * @return false, as this command does not trigger an application exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isChangingState() {
        return true;
    }
}
