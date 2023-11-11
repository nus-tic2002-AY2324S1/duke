package commands.add;

import commands.Command;
import duke.Storage;
import duke.UI;
import tasks.InvalidInputException;
import tasks.Task;
import tasks.TaskList;

public class AddDeadline extends Command {
    String date;
    String description;

    public AddDeadline(String line){
        // deadline buy food /by 5pm
        this.date = line.substring(line.indexOf("/by")+3).trim();
        this.description = line.substring(8, line.indexOf("/by")).trim();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addDeadline(date, description);
        ui.addedTask(t);
    }

    
}
