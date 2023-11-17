package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.util.IntegerUtils;

/**
 * The `MarkCommand` class represents a command to mark a task as done in the task list.
 * When executed, this command validates the user input, marks the specified task as done,
 * and updates the storage and user interface accordingly.
 */
public class MarkCommand extends AbstractCommand {
    /**
     * Instantiates a new `MarkCommand` with the provided arguments.
     *
     * @param args The user input containing the task number to be marked as done.
     */
    public MarkCommand(String args) {
        super(args);
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be marked cannot be empty.");
        }

        Integer taskNumber = IntegerUtils.tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be marked.");
        }

        AbstractTask task = tasks.markTask(taskNumber - 1);
        storage.save(tasks);
        return new String[] {
            "Nice! I've marked this task as done:",
            "  " + task
        };
    }
}
