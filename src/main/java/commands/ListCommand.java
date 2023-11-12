package commands;

import duke.Storage;
import duke.UI;
import exceptions.EmptyListException;
import tasks.TaskList;

public class ListCommand extends Command{
    /**
     * {@inheritDoc}
     * 
     * This implementation of {@code execute} lists every
     * {@code task} in the user's {@code tasks} list.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try{
            tasks.listItems();
        }
        catch(EmptyListException e){
            ui.showError(e.getMessage());
        }

    }
    
}
