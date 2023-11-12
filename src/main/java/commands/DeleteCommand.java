package commands;

import duke.Storage;
import duke.UI;
import exceptions.EmptyListException;
import exceptions.MissingTaskException;
import tasks.TaskList;

public class DeleteCommand extends Command{

    int item;
    public DeleteCommand(int input) {
        this.item = input;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try{
            tasks.deleteItem(item);
        }
        catch(EmptyListException e){
            ui.showError(e.getMessage());
            return;
        }
        catch (MissingTaskException e){
            ui.showError(e.getMessage());
            return;
        }
        ui.printMessage("That's one less thing to do! You now have " + tasks.totalTasks + " tasks left.");
        
    }
    
}
