package com.tina.command;

import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * Represents a Find command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Instantiates a new Find Command.
     * Finds a task by searching for the keyword.
     *
     * @param keyword the keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches the keyword in the task list.
     * Stores all matching task in the new task list.
     * Returns message and shows the matching task list.
     * Returns not found message if no task matches the keyword.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return message and matching task list, or not found message if no task matches
     *         the keyword.
     */
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
