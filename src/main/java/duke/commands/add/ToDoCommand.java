package duke.commands.add;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ToDoCommand extends Command {
    String description;

    public ToDoCommand(String line) throws DukeException {
        if (line.split(" ").length < 2)
            throw new DukeException("Oops, missing todo description!");
        this.description = line.split(" ", 2)[1].trim();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addToDo(description);
        ui.showTaskAdded(t);
    }
}
