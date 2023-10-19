package commands;

import data.TaskList;
import exceptions.DukeException;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    protected final String args;

    public Command(String args) {
        this.args = args;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
