package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * Represents a List command.
 * Shows all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Prints all tasks.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @return all tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }
}
