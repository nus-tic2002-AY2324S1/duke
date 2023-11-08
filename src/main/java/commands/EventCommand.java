package commands;

import storage.Storage;
import task.Event;
import task.Task;
import joshua.JoshuaUi;
import joshua.TaskList;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final Task eventToAdd;

    public EventCommand(String desc, String from, String to) {
        this.eventToAdd = new Event(desc, from, to);
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        taskList.addToTaskList(eventToAdd);
        ui.joshuaSays("ADDED TASK: " + eventToAdd);
    }
}
