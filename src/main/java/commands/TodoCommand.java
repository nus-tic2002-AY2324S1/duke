package commands;

import storage.Storage;
import task.Task;
import task.ToDo;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {
    private final Task todoToAdd;

    public TodoCommand(String desc) {
        this.todoToAdd = new ToDo(desc);
    }

    public Task getTodoToAdd() {
        return todoToAdd;
    }

    /**
     * {@inheritDoc} Adds a new ToDo task to the TaskList.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        taskList.addToTaskList(todoToAdd);
        ui.joshuaSays("ADDED TASK: " + todoToAdd);
    }
}
