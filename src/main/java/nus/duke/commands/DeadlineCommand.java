package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Deadline;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

import java.time.LocalDateTime;

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

        LocalDateTime by = Parser.parseUserDateTime(array[1]);
        Deadline deadline = new Deadline(array[0], by);
        tasks.addTask(deadline);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
