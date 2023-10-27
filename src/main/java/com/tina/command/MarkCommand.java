package com.tina.command;

import com.tina.Ui;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.task.Task;
import com.tina.task.TaskList;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        super(CommandEnum.MARK);
        this.taskNumber = taskNumber;
    }

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
