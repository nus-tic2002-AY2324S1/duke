package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents an ummark command.
 */
public class UnmarkCommand extends Command {
    private final int[] taskNums;

    public UnmarkCommand(int[] taskNums) {
        this.taskNums = taskNums;
    }

    /**
     * {@inheritDoc} Unmarks tasks based on the task numbers input by the user (stored in taskNums).
     *
     * @throws IndexOutOfBoundsException If a task number is not within the valid range.
     */
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
