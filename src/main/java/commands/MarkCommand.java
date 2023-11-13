package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int[] taskNums;

    public MarkCommand(int[] taskNums) {
        this.taskNums = taskNums;
    }

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
