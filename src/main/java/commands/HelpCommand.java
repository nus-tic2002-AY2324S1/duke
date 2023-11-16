package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

public class HelpCommand extends Command {

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} stores the current user
     * {@code tasks} list and terminates the program.
     * 
     * @param storage is not used in this implementation.
     * @param tasks is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.help();
    }
    
}
