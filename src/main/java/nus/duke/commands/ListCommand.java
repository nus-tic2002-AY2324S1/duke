package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Task;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The list command should not take any arguments.");
        }

        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            lines[i] = String.format("%d.%s", i + 1, task.toString());
        }
        ui.showMessages(lines);
    }
}
