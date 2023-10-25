package com.tina.command;

import com.tina.Ui;
import com.tina.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandEnum.BYE);
        this.isBye = true;
        this.syntax = "bye";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printBye();
    }
}
