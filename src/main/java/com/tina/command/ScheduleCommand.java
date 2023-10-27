package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.DeadlineTask;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleCommand extends Command {
    private final LocalDate date;
    public ScheduleCommand(LocalDate date) {
        super(CommandEnum.SCHEDULE);
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        TaskList tasks = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask)task;
                LocalDate by = deadlineTask.getBy();
                if (by.isEqual(date)) {
                    tasks.add(deadlineTask);
                }
            }
            else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask)task;
                LocalDate from = eventTask.getFrom();
                LocalDate to = eventTask.getTo();
                if (from.isBefore(date) && to.isAfter(date)) {
                    tasks.add(eventTask);
                }
            }
        }
        ui.printLine("Deadlines/Events occurring on " + date + ":");
        ui.printTaskList(tasks);
    }
}
