package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * Represents a bye command.
 * Exits the program.
 */
public class ByeCommand extends Command {

    /**
     * Returns bye message.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return bye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
