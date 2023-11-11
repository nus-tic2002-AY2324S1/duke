package commands;

import duke.Storage;
import duke.UI;
import tasks.EmptyListException;
import tasks.MissingTaskException;
import tasks.TaskList;

public class DeleteCommand extends Command{

    int item;
    public DeleteCommand(String input) {
        this.item = Integer.parseInt(input);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.printMessage("Removing task " + item + ":" + tasks.tasks.get(item));
        try{
            tasks.deleteItem(item);
        }
        catch(EmptyListException e){
            ui.showError(e.getMessage());
        }
        catch (MissingTaskException e){
            ui.showError(e.getMessage());
        }
        ui.printMessage("That's one less thing to do! You now have " + tasks.totalTasks + " tasks left.");
        
    }
    
}
