package com.tina.command;

import com.tina.exception.InvalidFilePathException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;
import java.time.LocalDate;

/**
 * Represents an Event command.
 * Creates an event task with a period from start date to end date.
 */
public class EventCommand extends Command {
    private final String taskName;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Instantiates a new Event command.
     *
     * @param taskName the task name.
     * @param from     the start date.
     * @param to       the end date.
     */
    public EventCommand(String taskName, LocalDate from, LocalDate to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Gets start date.
     *
     * @return the start date.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets end date.
     *
     * @return the end date.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Creates an event task and add it to task list.
     * Calls method from ui to print confirmation message.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return confirmation message.
     * @throws InvalidFilePathException if file path is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidFilePathException {
        Task newTask = new EventTask(taskName, from, to);
        taskList.add(newTask);
        storage.save(taskList);
        return ui.printTask(newTask, false, taskList.size());
    }
}
