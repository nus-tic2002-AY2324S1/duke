package nus.duke.commands;

import java.util.ArrayList;
import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;

/**
 * Represents a command to search for tasks based on a given criteria.
 */
public class FindCommand extends AbstractQueryCommand {

    /**
     * Creates a FindCommand with the specified search criteria.
     *
     * @param args The search criteria provided by the user.
     */
    public FindCommand(String args) {
        super(args);
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The find criteria cannot be empty.");
        }

        String criteria = args.trim();
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            AbstractTask task = tasks.getTask(i);
            if (task.getDescription().toLowerCase().contains(criteria.toLowerCase())) {
                lines.add(String.format("%d.%s", i + 1, task));
            }
        }
        return lines.toArray(String[]::new);
    }
}
