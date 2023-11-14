package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ByeCommand extends Command{
    /**
     * {@inheritDoc}
     * 
     * This implementation of {@code execute} stores/save the current user 
     * {@code tasks} list and kills the program.
     * 
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        storage.storeData(tasks);
        isExit = true;
        ui.killUI();
    }
    
}
