package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Deadline;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * The `DeadlineCommand` class represents a command to add a new deadline task.
 * When executed, it parses user input to create a deadline task with a description
 * and a specific due date/time, then adds the task to the task list.
 */
public class DeadlineCommand extends AbstractTaskCommand {
    /**
     * Instantiates a new `DeadlineCommand` with the provided arguments.
     *
     * @param args The user input containing the description and the due date/time of the deadline task.
     */
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
