package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * The type Bye command.
 * Exit program.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        super(CommandEnum.BYE);
        this.isBye = true;
    }

    /**
     * Call method from ui to print goodbye message.
     *
     * @param taskList the task list
     * @param ui       the ui
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
