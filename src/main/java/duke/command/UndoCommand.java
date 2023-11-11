package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public class UndoCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        TaskList lastState = History.undo();

        if (lastState != null) {
            taskList.setTaskList(lastState);
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
