package nus.duke.commands;

import java.time.LocalDateTime;
import java.util.Optional;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.TaskOptionKey;
import nus.duke.data.TaskSource;
import nus.duke.data.tasks.Event;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

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
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of an event cannot be empty.");
        }

        TaskSource taskSource = Parser.parseTaskSource(args);
        Optional<String> fromOption = taskSource.getOptionValue(TaskOptionKey.FROM);
        Optional<String> toOption = taskSource.getOptionValue(TaskOptionKey.TO);
        if (fromOption.isEmpty()) {
            throw new InvalidCommandArgsDukeException(
                String.format("The \"/%s {date/time}\" of an event is required.", TaskOptionKey.FROM));
        }
        if (toOption.isEmpty()) {
            throw new InvalidCommandArgsDukeException(
                String.format("The \"/%s {date/time}\" of an event is required.", TaskOptionKey.TO));
        }

        Optional<TaskAfterOption> optionalAfterOption = getAfterOption(tasks, taskSource);
        LocalDateTime from = Parser.parseUserDateTime(fromOption.get());
        LocalDateTime to = Parser.parseUserRelativeDateTime(from, toOption.get());
        Event event = new Event(taskSource.getDescription(), from, to);
        if (optionalAfterOption.isPresent()) {
            TaskAfterOption afterOption = optionalAfterOption.get();
            if (afterOption.isAfterTime()
                && (!afterOption.getDateTime().isAfter(from) || !afterOption.getDateTime().isBefore(to))) {
                throw new InvalidCommandArgsDukeException(
                    String.format(
                        "The time of \"/%s\" is not between \"/%s\" and \"/%s\".",
                        TaskOptionKey.AFTER,
                        TaskOptionKey.FROM,
                        TaskOptionKey.TO));
            }
            event.setAfterOption(optionalAfterOption.get());
        }
        tasks.addTask(event);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
