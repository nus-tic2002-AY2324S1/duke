package commands;

import storage.Storage;
import task.Deadline;
import task.Task;
import wargames.JoshuaUi;
import wargames.TaskList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final Task deadlineToAdd;

    public DeadlineCommand(String desc, String by) {
        this.deadlineToAdd = new Deadline(desc, by);
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        taskList.addToTaskList(deadlineToAdd);
        ui.joshuaSays("ADDED TASK: " + deadlineToAdd);
    }
}
