package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.TaskList;

public abstract class Command {
    private final CommandEnum commandType;
    boolean isBye = false;

    public Command(CommandEnum commandType) {
        this.commandType = commandType;
    }

    public CommandEnum getCommandType() {
        return commandType;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public boolean getIsBye() {
        return isBye;
    }

}
