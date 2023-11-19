package commands;

import storage.Storage;
import task.Task;
import joshua.JoshuaUi;
import joshua.TaskList;

import java.util.ArrayList;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private final int[] taskNums;

    public DeleteCommand(int[] taskNums) { this.taskNums = taskNums; }

    /**
     * {@inheritDoc} Deletes tasks based on the task numbers input by the user (stored in taskNums).
     *
     * @throws IndexOutOfBoundsException If a task number is not within the valid range.
     */
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
