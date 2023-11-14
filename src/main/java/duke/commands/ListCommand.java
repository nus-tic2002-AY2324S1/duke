package duke.commands;

import duke.exceptions.EmptyListException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     * 
     * This implementation of {@code execute} lists every {@code task} in the user's {@code tasks}
     * list.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.listItems();
        } catch (EmptyListException e) {
            ui.showError(e.getMessage());
        }

    }

}
