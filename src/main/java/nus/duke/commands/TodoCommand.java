package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Todo;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

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
