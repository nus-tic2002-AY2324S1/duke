package nus.duke.commands;

import nus.duke.common.Utils;
import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be unmarked cannot be empty.");
        }

        Integer taskNumber = Utils.tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be unmarked.");
        }

        AbstractTask task = tasks.getTask(taskNumber - 1);
        task.setDone(false);
        storage.save(tasks);
        ui.showMessages(new String[]{
                "OK, I've marked this task as not done yet:",
                "  " + task
        });
    }
}
