package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public class UndoCommand extends Command {

    /**
     * Executes the undo command, which reverts the task list to its previous state.
     * If there is a state to revert to, it will update the task list and inform the user of the success.
     * If not, it will inform the user that there is nothing to undo.
     *
     * @param taskList The task list that may be reverted to a previous state.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage where tasks are persisted. It is updated with the reverted task list.
     * @throws DukeException If a problem occurs during the execution of the command.
     * @throws IOException   If an I/O error occurs during saving the reverted state.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        TaskList lastState = History.undo();

        if (lastState != null) {
            taskList.setTaskList(lastState);
            Storage.save(taskList);
            UI.showUndoSuccess();
        } else {
            UI.showUndoFail();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isChangingState() {
        return false;
    }
}
