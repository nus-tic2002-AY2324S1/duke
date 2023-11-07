package commands;

import storage.Storage;
import wargames.JoshuaUi;
import wargames.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int taskNum;
    private final int taskIdx;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
        this.taskIdx = taskNum - 1;
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        try {
            taskList.markTaskAsDone(taskNum);
            ui.joshuaSays("Marked task: " + taskList.getItem(taskIdx));
        } catch (IndexOutOfBoundsException e) {
            ui.joshuaSays("Enter a number within the task list.");
        }
    }
}
