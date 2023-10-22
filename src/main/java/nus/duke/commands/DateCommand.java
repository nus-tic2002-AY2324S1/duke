package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedMap;

/**
 * The `DateCommand` class represents a command to list tasks with a specific due date.
 * It retrieves tasks from the task list that match the given date and displays them.
 */
public class DateCommand extends AbstractCommand {
    /**
     * Instantiates a new `DateCommand` with the provided arguments.
     *
     * @param args The due date specified in the command.
     */
    public DateCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The due date cannot be empty.");
        }

        LocalDate date = Parser.parseUserDate(args);
        SortedMap<Integer, AbstractTask> tasksOnDate = tasks.getTasks(date);

        ArrayList<String> lines = new ArrayList<>();
        tasksOnDate.forEach((index, task) -> {
            lines.add(String.format("%d.%s", index + 1, task));
        });
        ui.showMessages(lines.toArray(String[]::new));
    }
}
