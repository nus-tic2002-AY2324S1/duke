package commands;

import duke.Storage;
import duke.UI;
import exceptions.MissingTaskException;
import tasks.TaskList;

public class MarkCommand extends Command{
    int item;

    public MarkCommand(int input) { 
        this.item = input;
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
