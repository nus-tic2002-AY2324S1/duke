package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Event;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

import java.time.LocalDateTime;

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

        LocalDateTime from = Parser.parseUserDateTime(fromToArray[0]);
        LocalDateTime to = Parser.parseUserRelativeDateTime(from, fromToArray[1]);
        Event event = new Event(array[0], from, to);
        tasks.addTask(event);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
