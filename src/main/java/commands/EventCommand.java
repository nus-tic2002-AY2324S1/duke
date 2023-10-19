package commands;

import data.TaskList;
import data.tasks.Event;
import exceptions.DukeException;
import exceptions.InvalidCommandArgsDukeException;
import storage.Storage;
import ui.Ui;

public class EventCommand extends TaskCommand {
    public EventCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a event cannot be empty.");
        }

        String[] array = args.split(" /from ", -1);
        if (array.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/from {date/time}\" of a event is required.");
        }

        String[] fromToArray = array[1].split(" /to ", -1);
        if (fromToArray.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/to {date/time}\" of a event is required.");
        }

        Event event = new Event(array[0], fromToArray[0], fromToArray[1]);
        tasks.addTask(event);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
