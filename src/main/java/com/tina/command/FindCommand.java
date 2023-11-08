package com.tina.command;

import com.tina.exception.ExistedFileException;
import com.tina.exception.IOException;
import com.tina.exception.InvalidFilePathException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Instantiates a new Find Command.
     * Find a task by seaching for a keyword
     *
     * @param keyword the keyword
     */
    public FindCommand(String keyword) {
        super(CommandEnum.FIND);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList tasks = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                tasks.add(task);
            }
        }
        ui.printLine("Here are the matching tasks in your list:");
        ui.printTaskList(tasks);
    }
}
