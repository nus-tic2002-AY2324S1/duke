package commands;

import common.Utils;
import data.TaskList;
import data.tasks.Task;
import exceptions.DukeException;
import exceptions.InvalidCommandArgsDukeException;
import storage.Storage;
import ui.Ui;

public class UnmarkCommand extends Command {
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

        Task task = tasks.getTask(taskNumber - 1);
        task.setDone(false);
        storage.save(tasks);
        ui.showMessages(new String[]{
                "OK, I've marked this task as not done yet:",
                "  " + task
        });
    }
}
