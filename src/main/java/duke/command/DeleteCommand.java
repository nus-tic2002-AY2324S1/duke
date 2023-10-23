package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try{
            Task deletedTask = taskList.deleteTask(index);
            UI.showTaskDeleted(deletedTask, taskList);
        } catch (DukeException | IOException e) {
            UI.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
