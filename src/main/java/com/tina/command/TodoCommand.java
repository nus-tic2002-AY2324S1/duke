package com.tina.command;

import com.tina.Ui;
import com.tina.exception.DukeException;
import com.tina.task.Task;
import com.tina.task.TaskList;
import com.tina.task.TodoTask;

public class TodoCommand extends Command {
    private final String taskName;

    public TodoCommand(String taskName) {
        super(CommandEnum.TODO);
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task newTask = new TodoTask(taskName);
        taskList.add(newTask);
        ui.printTask(newTask, false, taskList.size());
    }

    public String getTaskName() {
        return taskName;
    }
}
