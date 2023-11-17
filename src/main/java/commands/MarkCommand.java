package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    private final int[] taskNums;

    public MarkCommand(int[] taskNums) {
        this.taskNums = taskNums;
    }

    /**
     * {@inheritDoc} Marks tasks as done based on the task numbers input by the user (stored in taskNums).
     *
     * @throws IndexOutOfBoundsException If a task number is not within the valid range.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        for (int taskNum : taskNums) {
            try {
                taskList.markTaskAsDone(taskNum);
                ui.joshuaSays("Marked task: " + taskList.getTask(taskNum));
            } catch (IndexOutOfBoundsException e) {
                ui.joshuaSays(taskNum + " is not a valid task number. Enter a number from the task list.");
            }
        }
    }
}
