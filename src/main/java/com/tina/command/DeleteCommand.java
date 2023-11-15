package com.tina.command;

import com.tina.exception.InvalidFilePathException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * The type Delete command.
 * By given the task number, delete the task from task list
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Instantiates a new Delete command.
     *
     * @param taskNumber the task number to be deleted
     */
    public DeleteCommand(int taskNumber) {
        super(CommandEnum.DELETE);
        this.taskNumber = taskNumber;
    }

    /**
     * Get the task by given task number, throw error if the task is not found.
     * Delete the task from task list.
     * Call method from ui to print confirmation message.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @throws InvalidTaskNumberException if the task is not found.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException, InvalidFilePathException {
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
