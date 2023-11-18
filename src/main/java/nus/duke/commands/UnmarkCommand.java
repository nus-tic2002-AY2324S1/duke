package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.util.IntegerUtils;

/**
 * The `UnmarkCommand` class represents a command to unmark a task as not done in the task list.
 * When executed, this command validates the user input, unmarks the specified task, and
 * updates the storage and user interface accordingly.
 */
public class UnmarkCommand extends AbstractCommand {
    /**
     * Instantiates a new `UnmarkCommand` with the provided arguments.
     *
     * @param args The user input containing the task number to be unmarked.
     */
    public UnmarkCommand(String args) {
        super(args);
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be unmarked cannot be empty.");
        }

        Integer taskNumber = IntegerUtils.tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be unmarked.");
        }


        AbstractTask task = tasks.unmarkTask(taskNumber - 1);
        storage.save(tasks);
        return new String[] {
            "OK, I've marked this task as not done yet:",
            "  " + task
        };
    }
}
