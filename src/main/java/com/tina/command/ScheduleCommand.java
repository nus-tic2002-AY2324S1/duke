package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.DeadlineTask;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDate;

/**
 * The type Schedule command.
 * Show all the tasks related to the given date.
 */
public class ScheduleCommand extends Command {
    private final LocalDate date;

    /**
     * Instantiates a new Schedule command.
     *
     * @param date the date
     */
    public ScheduleCommand(LocalDate date) {
        super(CommandEnum.SCHEDULE);
        this.date = date;
    }

    /**
     * Iterate the task list and find the task that related to the given date.
     * Add these found tasks to a new task list.
     * Call method from ui to print the new task list.
     *
     * @param taskList the task list
     * @param ui       the ui
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList tasks = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                LocalDate by = deadlineTask.getBy();
                if (by.isEqual(date)) {
                    tasks.add(deadlineTask);
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                LocalDate from = eventTask.getFrom();
                LocalDate to = eventTask.getTo();
                if (from.isBefore(date) && to.isAfter(date)) {
                    tasks.add(eventTask);
                }
            }
        }
        return "Deadlines/Events occurring on " + date + ":\n" + ui.printTaskList(tasks);
    }
}
