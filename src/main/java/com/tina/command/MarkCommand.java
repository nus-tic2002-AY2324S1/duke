package com.tina.command;

import com.tina.Ui;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * The type Mark command.
 * Mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates a new Mark command.
     *
     * @param taskNumber the task number
     */
    public MarkCommand(int taskNumber) {
        super(CommandEnum.MARK);
        this.taskNumber = taskNumber;
    }

    /**
     * Get the task by given task number, throw error if the task is not found.
     * Mark the task as done.
     * Call method from ui to print confirmation message.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @throws InvalidTaskNumberException if the task is not found
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
        try {
            Task curTask = taskList.getTaskList().get(taskNumber - 1);
            curTask.setDone(true);
            ui.printMark(curTask, true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

}
