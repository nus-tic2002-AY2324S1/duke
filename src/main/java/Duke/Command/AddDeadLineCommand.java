package Duke.Command;

import Duke.Task.DeadlineTask;
import Duke.Task.Task;

public class AddDeadLineCommand extends AddTaskCommand {
    private final String taskDueDate;

    public AddDeadLineCommand(String taskName, String taskDueDate) {
        super(taskName);
        this.taskDueDate = taskDueDate;
    }

    @Override
    public void execute() {
        Task task = new DeadlineTask(taskName, taskDueDate);
        addTask(task);
    }
}
