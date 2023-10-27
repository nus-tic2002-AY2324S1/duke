package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.task.Task;
import com.tina.task.TaskList;

public class DeleteCommand extends Command {

    private final int taskNumber;
    public DeleteCommand(int taskNumber) {
        super(CommandEnum.DELETE);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            Task curTask = taskList.getTaskList().get(taskNumber - 1);
            taskList.remove(curTask);
            ui.printTask(curTask, true, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
