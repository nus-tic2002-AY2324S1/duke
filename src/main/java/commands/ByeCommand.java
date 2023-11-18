package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.UI;

public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} stores the current user {@code tasks} list and terminates
     * the program.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            storage.storeData(tasks);
            isExit = true;

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.killUI();
        }

    }

}
