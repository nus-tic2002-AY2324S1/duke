package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String taskName;
    private final LocalDate from;
    private final LocalDate to;

    public EventCommand(String taskName, LocalDate from, LocalDate to) {
        super(CommandEnum.EVENT);
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task newTask = new EventTask(taskName, from, to);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }
}
