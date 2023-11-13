package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int[] taskNums;

    public UnmarkCommand(int[] taskNums) {
        this.taskNums = taskNums;
    }

    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        for (int taskNum : taskNums) {
            try {
                taskList.markTaskAsNotDone(taskNum);
                ui.joshuaSays("Unmarked task: " + taskList.getTask(taskNum));
            } catch (IndexOutOfBoundsException e) {
                ui.joshuaSays(taskNum + " is not a valid task number. Enter a number from the task list.");
            }
        }
    }
}
