package commands;

import storage.Storage;
import wargames.JoshuaUi;
import wargames.TaskList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int taskNum;
    private final int taskIdx;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
        this.taskIdx = taskNum - 1;
    }

    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        try {
            taskList.markTaskAsNotDone(taskNum);
            ui.joshuaSays("Unmarked task: " + taskList.getItem(taskIdx));
        } catch (IndexOutOfBoundsException e) {
            ui.joshuaSays("Enter a number from the task list.");
        }
    }
}
