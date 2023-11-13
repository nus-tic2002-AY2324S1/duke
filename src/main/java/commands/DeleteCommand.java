package commands;

import storage.Storage;
import task.Task;
import joshua.JoshuaUi;
import joshua.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int[] taskNums;

    public DeleteCommand(int[] taskNums) { this.taskNums = taskNums; }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ArrayList<Task> tasksToDelete = new ArrayList<>();
        for (int taskNum : taskNums) {
            try {
                Task task = taskList.getTask(taskNum);
                tasksToDelete.add(task);
            } catch (IndexOutOfBoundsException e) {
                ui.joshuaSays(taskNum + " is not a valid task number. Enter a number from the task list.");
                return;
            }
        }

        ui.joshuaSays("The following task(s) will be deleted:");
        for (Task task : tasksToDelete) {
            ui.joshuaSays("\t" + task);
            taskList.deleteFromTaskList(task);
        }
        ui.joshuaSays("You now have " + taskList.listSize() + " item(s) in your list.");
    }
}
