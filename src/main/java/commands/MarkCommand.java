package commands;

import commands.Command;
import common.Utils;
import data.TaskList;
import data.tasks.Task;
import exceptions.DukeException;
import exceptions.InvalidCommandArgsDukeException;
import storage.Storage;
import ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be marked cannot be empty.");
        }

        Integer taskNumber = Utils.tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be marked.");
        }

        Task task = tasks.getTask(taskNumber - 1);
        task.setDone(true);
        storage.save(tasks);
        ui.showMessages(new String[]{
                "Nice! I've marked this task as done:",
                "  " + task.toString()
        });
    }
}
