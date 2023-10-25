package com.tina.command;

import com.tina.Ui;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.task.Task;
import com.tina.task.TaskList;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super(CommandEnum.UNMARK);
        this.taskNumber = taskNumber;
        this.syntax = "unmark [task number]";
    }

    public void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
        try {
            Task curTask = taskList.getTaskList().get(taskNumber - 1);
            curTask.setDone(false);
            ui.printMark(curTask, false);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

}
