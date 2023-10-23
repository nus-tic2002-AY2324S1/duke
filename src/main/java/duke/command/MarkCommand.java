package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try{
            taskList.getTaskList().get(index).markAsDone();
            Task markAsDOneTask = taskList.markTaskAsDone(index);
            UI.showTaskMarkedAsDone(markAsDOneTask);
        } catch (DukeException | IOException e) {
            UI.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
