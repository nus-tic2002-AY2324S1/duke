package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.TaskList;

public abstract class Command {
    private final CommandEnum commandName;
    boolean isBye = false;
    String syntax;

    public Command(CommandEnum commandName) {
        this.commandName = commandName;
    }

    public CommandEnum getCommandName() {
        return commandName;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public boolean getIsBye() {
        return isBye;
    }

    public String getSyntax() {
        return syntax;
    }
}
