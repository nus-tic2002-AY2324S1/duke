package commands;

import duke.*;
import duke.UI;
import tasks.TaskList;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        storage.storeData(tasks);
        isExit = true;
        ui.killUI();
    }
    
}
