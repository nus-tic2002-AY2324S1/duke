package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        UI.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
