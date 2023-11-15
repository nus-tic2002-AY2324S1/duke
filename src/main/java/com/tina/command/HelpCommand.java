package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * Represents a Help command.
 */
public class HelpCommand extends Command {

    /**
     * Prints the command list for user reference.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return command list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printCommandList();
    }
}
