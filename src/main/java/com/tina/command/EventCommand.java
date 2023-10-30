package com.tina.command;

import com.tina.service.Ui;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDate;

/**
 * The type Event command.
 * Create an event task with a period from start date to end date.
 */
public class EventCommand extends Command {
    private final String taskName;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Instantiates a new Event command.
     *
     * @param taskName the task name
     * @param from     the start date
     * @param to       the end date
     */
    public EventCommand(String taskName, LocalDate from, LocalDate to) {
        super(CommandEnum.EVENT);
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Gets task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Create an event task and add it to task list.
     * Call method from ui to print confirmation message.
     *
     * @param taskList the task list
     * @param ui       the ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task newTask = new EventTask(taskName, from, to);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }
}
