package Duke.Command;

import Duke.Task.DeadlineTask;
import Duke.Task.Task;

import java.time.LocalDateTime;

public class AddDeadLineCommand extends AddTaskCommand {
    private final LocalDateTime taskDueDate;

    public AddDeadLineCommand(String taskName, LocalDateTime taskDueDate) {
        super(taskName);
        this.taskDueDate = taskDueDate;
    }

    @Override
    public void execute() {
        Task task = new DeadlineTask(taskName, taskDueDate);
        addTask(task);
    }
}
