package com.tina.command;

import com.tina.exception.InvalidFilePathException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * The type Unmark command.
 * Mark the task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates a new Unmark command.
     *
     * @param taskNumber the task number
     */
    public UnmarkCommand(int taskNumber) {
        super(CommandEnum.UNMARK);
        this.taskNumber = taskNumber;
    }

    /**
     * Get the task by given task number, throw error if the task is not found.
     * Mark the task as not done.
     * Call method from ui to print confirmation message.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @throws InvalidTaskNumberException if the task is not found
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
