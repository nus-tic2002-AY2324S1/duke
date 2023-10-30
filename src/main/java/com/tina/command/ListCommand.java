package com.tina.command;

import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * The type List command.
 * Show all tasks in the task list.
 */
public class ListCommand extends Command{
    /**
     * Instantiates a new List command.
     */
    public ListCommand() {
        super(CommandEnum.LIST);
    }

    /**
     * Call method from ui to print all tasks.
     *
     * @param taskList the task list
     * @param ui       the ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printTaskList(taskList);
    }
}
