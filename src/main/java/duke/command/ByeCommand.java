package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        UI.showBye();
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return Always returns true, as bye a task to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
