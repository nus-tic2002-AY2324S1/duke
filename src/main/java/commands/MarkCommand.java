package commands;

import duke.Storage;
import duke.UI;
import tasks.MissingTaskException;
import tasks.TaskList;

public class MarkCommand extends Command{
    int item;

    public MarkCommand(String input) { 
        this.item = Integer.parseInt(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.markItem(item);
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }
    }
    
}
