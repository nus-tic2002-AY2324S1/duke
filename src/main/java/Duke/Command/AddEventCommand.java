package Duke.Command;

import Duke.Task.EventTask;
import Duke.Task.Task;

public class AddEventCommand extends AddTaskCommand {

    private final String taskFromDate;
    private final String taskToDate;

    public AddEventCommand(String taskName, String taskFromDate, String taskToDate) {
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
