package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        super(CommandEnum.EVENT);
        this.taskName = taskName;
        this.from = from;
        this.to = to;
        this.syntax = "event [task name] /from [date] /to [date]";
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task newTask = new EventTask(taskName, from, to);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }
}
