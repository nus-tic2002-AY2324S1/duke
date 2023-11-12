package commands;

import duke.Storage;
import duke.UI;
import exceptions.MissingTaskException;
import tasks.TaskList;

public class UnmarkCommand extends Command{
    int item;

    public UnmarkCommand(int input) { 
        this.item = input;
    }

    /**
     * {@inheritDoc}
     * 
     * This implementation of {@code execute} unmarks the specified
     * {@code task} object from the user's {@code tasks}.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.unmarkItem(item);
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }

        
    }
    
}
