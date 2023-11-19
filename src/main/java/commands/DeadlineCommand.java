package commands;

import storage.Storage;
import task.Deadline;
import task.Task;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    private final Task deadlineToAdd;

    public DeadlineCommand(String desc, String by) {
        this.deadlineToAdd = new Deadline(desc, by);
    }

    public Task getDeadlineToAdd() {
        return deadlineToAdd;
    }

    /**
     * {@inheritDoc} Adds a new Deadline task to the TaskList.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        taskList.addToTaskList(deadlineToAdd);
        ui.joshuaSays("ADDED TASK: " + deadlineToAdd);
    }
}
