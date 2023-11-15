package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(CommandEnum.HELP);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printCommandList();
    }
}
