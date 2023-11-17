package commands;

import joshua.JoshuaUi;
import joshua.TaskList;
import storage.Storage;
import task.Task;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    private final String findStr;

    public FindCommand(String findStr) {
        this.findStr = findStr.toLowerCase();
    }

    /**
     * {@inheritDoc} Prints all tasks containing the findStr in its task description by iterating
     * through each task in the task list.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        if (findStr.isEmpty()) {
            ui.joshuaSays("Please enter a word for me to find.");
            return;
        }
        int count = 0;

        StringBuilder tasksStr = new StringBuilder();
        for (int taskNum = 1; taskNum <= taskList.listSize(); taskNum++) {
            Task task = taskList.getTask(taskNum);
            String desc = task.getDesc().toLowerCase();
            if (desc.contains(findStr)) {
                count++;
                tasksStr.append(taskNum).append(". ").append(task).append("\n");
            }
        }

        if (count == 0) {
            ui.joshuaSays("No matching tasks found.");
        }
        else {
            ui.joshuaSays("Found " + count + " task(s) matching \"" + findStr + "\":");
            ui.joshuaSays(tasksStr.toString());
        }
    }
}
