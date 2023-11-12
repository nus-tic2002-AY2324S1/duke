package commands.add;

import commands.Command;
import duke.Storage;
import duke.UI;
import duke.DukeException;
import tasks.Task;
import tasks.TaskList;

public class AddToDo extends Command{
    String description;

    public AddToDo(String line)throws DukeException{
        if (line.split(" ").length<2) throw new DukeException("Oops, missing todo description!");
        this.description = line.split(" ",2)[1].trim();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addToDo(description);
        ui.addedTask(t);
    }
}
