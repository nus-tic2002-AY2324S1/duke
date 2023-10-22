package Duke.Command;

import Duke.Task.EventTask;
import Duke.Task.Task;

import java.time.LocalDateTime;

public class AddEventCommand extends AddTaskCommand {

    private final LocalDateTime taskFromDate;
    private final LocalDateTime taskToDate;

    public AddEventCommand(String taskName, LocalDateTime taskFromDate, LocalDateTime taskToDate) {
        super(taskName);
        this.taskFromDate = taskFromDate;
        this.taskToDate = taskToDate;
    }

    @Override
    public void execute() {
        Task task = new EventTask(taskName, taskFromDate, taskToDate);
        addTask(task);
    }

}
