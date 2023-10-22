package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Event;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * The `EventCommand` class represents a command to add a new event task.
 * When executed, it parses user input to create an event task with a description
 * and specific start and end date/time, and then adds the task to the task list.
 */
public class EventCommand extends AbstractTaskCommand {
    /**
     * Instantiates a new `EventCommand` with the provided arguments.
     *
     * @param args The user input containing the event description, start date/time, and end date/time.
     */
    public EventCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of an event cannot be empty.");
        }

        String[] array = args.split(" /from ", -1);
        if (array.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/from {date/time}\" of an event is required.");
        }

        String[] fromToArray = array[1].split(" /to ", -1);
        if (fromToArray.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/to {date/time}\" of an event is required.");
        }

        LocalDateTime from = Parser.parseUserDateTime(fromToArray[0]);
        LocalDateTime to = Parser.parseUserRelativeDateTime(from, fromToArray[1]);
        Event event = new Event(array[0], from, to);
        tasks.addTask(event);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
