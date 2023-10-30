package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.task.TaskList;

/**
 * The parent Command class.
 * It contains command type and a flag that indicate the class is or is not a byb command.
 */
public abstract class Command {
    private final CommandEnum commandType;
    /**
     * A flag that indicate the Command class is or is not a BybCommand class
     */
    boolean isBye = false;

    /**
     * Instantiates a new Command.
     *
     * @param commandType the command type
     */
    public Command(CommandEnum commandType) {
        this.commandType = commandType;
    }

    /**
     * Execute the action related to the command type
     *
     * @param taskList the task list
     * @param ui       the ui
     * @throws InvalidTaskNumberException the invalid task number exception
     */
    public abstract void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException;

    /**
     * Gets is bye flag.
     *
     * @return the is bye
     */
    public boolean getIsBye() {
        return isBye;
    }

}
