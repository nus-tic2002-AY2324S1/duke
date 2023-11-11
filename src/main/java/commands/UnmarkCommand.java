package commands;

import duke.Storage;
import duke.UI;
import tasks.MissingTaskException;
import tasks.TaskList;

public class UnmarkCommand extends Command{
    int item;

    public UnmarkCommand(String input) { 
        this.item = Integer.parseInt(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.unmarkItem(item);
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }

        
    }
    
}
