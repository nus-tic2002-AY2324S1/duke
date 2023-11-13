package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        try {
            taskList.markTaskAsNotDone(taskNum);
            ui.joshuaSays("Unmarked task: " + taskList.getTask(taskNum));
        } catch (IndexOutOfBoundsException e) {
            ui.joshuaSays("Enter a number from the task list.");
        }
    }
}
