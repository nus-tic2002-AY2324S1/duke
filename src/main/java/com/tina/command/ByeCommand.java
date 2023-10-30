package com.tina.command;

import com.tina.Ui;
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
    public void execute(TaskList taskList, Ui ui) {
        ui.printBye();
    }
}
