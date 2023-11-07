package commands;

import exceptions.InvalidCommandException;
import storage.Storage;
import task.Task;
import task.ToDo;
import wargames.JoshuaUi;
import wargames.TaskList;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final Task todoToAdd;

    public TodoCommand(String desc) {
        this.todoToAdd = new ToDo(desc);
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        taskList.addToTaskList(todoToAdd);
        ui.joshuaSays("ADDED TASK: " + todoToAdd);
    }
}
