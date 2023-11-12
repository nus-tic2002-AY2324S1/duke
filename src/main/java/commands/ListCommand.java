package commands;

import duke.Storage;
import duke.UI;
import exceptions.EmptyListException;
import tasks.TaskList;

public class ListCommand extends Command{
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
