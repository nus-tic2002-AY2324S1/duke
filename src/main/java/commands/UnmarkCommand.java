package commands;

import duke.Storage;
import duke.UI;
import exceptions.DukeException;
import exceptions.MissingTaskException;
import tasks.TaskList;

public class UnmarkCommand extends Command{
    int item;

    public UnmarkCommand(String userInput) throws DukeException { 
        if (userInput.split(" ").length < 2){
            throw new DukeException("Missing item number!");
        }
        try{
            this.item = Integer.parseInt(userInput.split(" ")[1]);
        }
        catch (NumberFormatException e){
            throw new DukeException("Ensure the item number is a valid integer!");
        }   
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
