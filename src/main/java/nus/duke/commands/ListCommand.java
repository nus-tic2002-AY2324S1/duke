package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;

/**
 * The `ListCommand` class represents a command to list all tasks in the task list.
 * When executed, this command retrieves all tasks and displays them, including their
 * respective task numbers.
 */
public class ListCommand extends AbstractQueryCommand {
    /**
     * Instantiates a new `ListCommand`.
     *
     * @param args The user input arguments (should be empty for the list command).
     */
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The list command should not take any arguments.");
        }

        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            AbstractTask task = tasks.getTask(i);
            lines[i] = String.format("%d.%s", i + 1, task.toString());
        }
        return lines;
    }
}
