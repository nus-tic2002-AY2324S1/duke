package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.DeadlineTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime by;
    public DeadlineCommand(String taskName, LocalDateTime by) {
        super(CommandEnum.DEADLINE);
        this.taskName = taskName;
        this.by = by;
        this.syntax = "deadline [task name] /by [date]";
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task newTask = new DeadlineTask(taskName, by);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }
}
