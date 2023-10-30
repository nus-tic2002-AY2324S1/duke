package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.Task;
import com.tina.task.TaskList;
import com.tina.task.TodoTask;

/**
 * The type Todo command.
 * Create a todo task.
 */
public class TodoCommand extends Command {
    private final String taskName;

    /**
     * Instantiates a new Todo command.
     *
     * @param taskName the task name
     */
    public TodoCommand(String taskName) {
        super(CommandEnum.TODO);
        this.taskName = taskName;
    }

    /**
     * Create a todo task and add it to task list.
     * Call method from ui to print confirmation message.
     *
     * @param taskList the task list
     * @param ui       the ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task newTask = new TodoTask(taskName);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }

    /**
     * Gets task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }
}
