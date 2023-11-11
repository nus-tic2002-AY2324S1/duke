package commands;

import duke.Storage;
import duke.UI;
import tasks.InvalidInputException;
import tasks.TaskList;

public class AddCommand extends Command {
    String input;

    public AddCommand(String input) {
        this.input = input;

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try{
            tasks.addItem(input);
        }
        catch(InvalidInputException e){
            ui.showError(e.getMessage());
        }
        
    }
    
}
