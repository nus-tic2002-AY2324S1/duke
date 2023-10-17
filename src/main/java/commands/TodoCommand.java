package commands;

import commands.TaskCommand;
import data.TaskList;
import data.tasks.Todo;
import exceptions.DukeException;
import exceptions.InvalidCommandArgsDukeException;
import storage.Storage;
import ui.Ui;

public class TodoCommand extends TaskCommand {
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a todo cannot be empty.");
        }

        Todo toto = new Todo(args);
        tasks.addTask(toto);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
