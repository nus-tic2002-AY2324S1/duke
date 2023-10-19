package commands;

import data.TaskList;
import data.tasks.Deadline;
import exceptions.DukeException;
import exceptions.InvalidCommandArgsDukeException;
import storage.Storage;
import ui.Ui;

public class DeadlineCommand extends TaskCommand {
    public DeadlineCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a deadline cannot be empty.");
        }

        String[] array = args.split(" /by ", -1);
        if (array.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/by {date/time}\" of a deadline is required.");
        }

        Deadline deadline = new Deadline(array[0], array[1]);
        tasks.addTask(deadline);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
