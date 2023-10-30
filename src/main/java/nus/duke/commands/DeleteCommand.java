package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;
import nus.duke.util.IntegerUtils;

/**
 * The `DeleteCommand` class represents a command to delete a task from the task list.
 * When executed, this command validates the user input, deletes the specified task,
 * and updates the storage and user interface accordingly.
 */
public class DeleteCommand extends AbstractCommand {
    /**
     * Instantiates a new `DeleteCommand` with the provided arguments.
     *
     * @param args The user input containing the task number to be deleted.
     */
    public DeleteCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be deleted cannot be empty.");
        }

        Integer taskNumber = IntegerUtils.tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be deleted.");
        }

        AbstractTask task = tasks.getTask(taskNumber - 1);
        tasks.removeTask(taskNumber - 1);
        storage.save(tasks);
        ui.showMessages(new String[]{
                "Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", tasks.size())
        });
    }
}
