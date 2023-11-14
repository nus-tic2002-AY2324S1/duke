package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ByeCommand extends Command {
    
    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} stores the current user
     * {@code tasks} list and terminates the program.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        storage.storeData(tasks);
        isExit = true;
        ui.killUI();
    }

}
