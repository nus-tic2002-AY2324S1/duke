package com.tina.command;

import com.tina.service.Ui;
import com.tina.task.DeadlineTask;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.time.LocalDate;


/**
 * The type Deadline command.
 * Create a deadline task with deadline date
 */
public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDate by;

    /**
     * Instantiates a new Deadline command.
     *
     * @param taskName the task name
     * @param by       the deadline date
     */
    public DeadlineCommand(String taskName, LocalDate by) {
        super(CommandEnum.DEADLINE);
        this.taskName = taskName;
        this.by = by;
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
     * Gets deadline date.
     *
     * @return the deadline date
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Create a deadline task and add it to task list.
     * Call method from ui to print the confirmation message.
     *
     * @param taskList the task list
     * @param ui       the ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task newTask = new DeadlineTask(taskName, by);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }
}
