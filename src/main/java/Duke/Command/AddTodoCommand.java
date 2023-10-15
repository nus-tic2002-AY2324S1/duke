package Duke.Command;

import Duke.Task.Task;
import Duke.Task.TodoTask;

public class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String taskName) {
        super(taskName);
    }

    @Override
    public void execute() {
        Task task = new TodoTask(taskName);
        addTask(task);
    }
}
