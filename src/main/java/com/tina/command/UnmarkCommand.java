package com.tina.command;

import com.tina.exception.InvalidFilePathException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * Represents a Unmark command.
 * Marks the task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates a new Unmark command.
     *
     * @param taskNumber the task number.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Gets the task by given task number, throw error if the task is not found.
     * Marks the task as not done.
     * Calls method from ui to print confirmation message.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return confirmation message.
     * @throws InvalidTaskNumberException if task number is invalid.
     * @throws InvalidFilePathException   if file path is invalid.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException, InvalidFilePathException {
        try {
            Task curTask = taskList.getTaskList().get(taskNumber - 1);
            curTask.setDone(false);
            storage.save(taskList);
            return ui.printMark(curTask, false);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

}
