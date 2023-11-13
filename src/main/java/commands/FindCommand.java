package commands;

import joshua.JoshuaUi;
import joshua.TaskList;
import storage.Storage;
import task.Task;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String findStr;

    public FindCommand(String findStr) {
        this.findStr = findStr.toLowerCase();
    }

    /**
     * Iterates through the tasks in the taskList and looks for tasks containing the findStr in the
     * task description.
     *
     * @param taskList The task list from the current session
     * @param ui JoshuaUI object for printing responses
     * @param storage Storage object to load or save to joshua.txt
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
                tasksStr.append(taskNum).append(". ").append(taskList.getTask(taskNum)).append("\n");
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
