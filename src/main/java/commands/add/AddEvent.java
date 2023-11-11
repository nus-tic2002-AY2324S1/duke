package commands.add;

import commands.Command;
import duke.Storage;
import duke.UI;
import tasks.TaskList;
import tasks.Task;

public class AddEvent extends Command {
    public String description;
    public String fromDate;
    public String toDate;
    
    public AddEvent(String input){
        this.description = input.substring(5, input.indexOf("/from")).trim();
        this.fromDate = input.substring(input.indexOf("/from")+5, input.indexOf("/to")).trim();
        this.toDate = input.substring(input.indexOf("/to")+3).trim();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addEvent(description, fromDate, toDate);
        ui.addedTask(t);
    }
}
