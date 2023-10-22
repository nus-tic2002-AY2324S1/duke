package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Task;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedMap;

public class DateCommand extends Command {
    public DateCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The due date cannot be empty.");
        }

        LocalDate date = Parser.parseUserDate(args);
        SortedMap<Integer, Task> tasksOnDate = tasks.getTasks(date);

        ArrayList<String> lines = new ArrayList<>();
        tasksOnDate.forEach((index, task) -> {
            lines.add(String.format("%d.%s", index + 1, task));
        });
        ui.showMessages(lines.toArray(String[]::new));
    }
}
