package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new TodoTask(taskName);
        taskList.add(newTask);
        return ui.printTask(newTask, false, taskList.size());
    }
}
