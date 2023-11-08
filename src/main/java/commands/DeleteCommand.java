package commands;

import storage.Storage;
import task.Task;
import joshua.JoshuaUi;
import joshua.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskNum;
    private final int taskIdx;

    public DeleteCommand(int taskNum){
        this.taskNum = taskNum;
        this.taskIdx = taskNum - 1;
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        try {
            Task task = taskList.getItem(taskIdx);
            ui.joshuaSays("The following task will be deleted:\n\t" + task.toString());
            taskList.deleteFromTaskList(task);
            ui.joshuaSays("You now have " + taskList.listSize() + " item(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            ui.joshuaSays("Enter a number from the task list.");
        }
    }
}
