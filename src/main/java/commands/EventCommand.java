package commands;

import storage.Storage;
import task.Event;
import task.Task;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {
    private final Task eventToAdd;

    public EventCommand(String desc, String from, String to) {
        this.eventToAdd = new Event(desc, from, to);
    }

    /**
     * {@inheritDoc} Adds a new Event task to the TaskList.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        taskList.addToTaskList(eventToAdd);
        ui.joshuaSays("ADDED TASK: " + eventToAdd);
    }
}
