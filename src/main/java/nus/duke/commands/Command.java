package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.exceptions.DukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

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
