package com.tina.command;

import com.tina.Ui;
import com.tina.task.TaskList;

public class ListCommand extends Command{
    public ListCommand() {
        super(CommandEnum.LIST);
        this.syntax = "list";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printTaskList(taskList);
    }
}
