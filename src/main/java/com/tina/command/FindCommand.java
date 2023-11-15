package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.Task;
import com.tina.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Instantiates a new Find Command.
     * Find a task by searching for a keyword
     *
     * @param keyword the keyword
     */
    public FindCommand(String keyword) {
        super(CommandEnum.FIND);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList tasks = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                tasks.add(task);
            }
        }
        if (tasks.size() == 0) {
            return "No matching tasks found.";
        } else {
            return "Here are the matching tasks in your list:\n" + ui.printTaskList(tasks);
        }
    }
}
