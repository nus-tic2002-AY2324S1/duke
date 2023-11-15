package com.tina.command;

import com.tina.exception.ExistedFileException;
import com.tina.exception.IOException;
import com.tina.exception.InvalidFilePathException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * Represents the parent Command class.
 */
public abstract class Command {
    /**
     * Executes the action according to specific command.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return message to be displayed to user.
     * @throws InvalidTaskNumberException   if task number is invalid.
     * @throws InvalidFilePathException     if file path is invalid.
     * @throws ExistedFileException         if file is already existed.
     * @throws IOException                  if any IO error occurs.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException, InvalidFilePathException, ExistedFileException, IOException;
}
