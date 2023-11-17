package com.tina.command;

import com.tina.exception.InvalidFilePathException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * Represents a Delete command.
 * Deletes the task from task list.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Instantiates a new Delete command.
     *
     * @param taskNumber the task number to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Gets the task by given task number.
     * Throws error if the task is not found.
     * Deletes the task from task list.
     * Calls method from ui to print confirmation message.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return confirmation message.
     * @throws InvalidTaskNumberException if the task is not found.
     * @throws InvalidFilePathException   if file path is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException,
            InvalidFilePathException {
        try {
            Task curTask = taskList.getTaskList().get(taskNumber - 1);
            taskList.remove(curTask);
            storage.save(taskList);
            return ui.printTask(curTask, true, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
