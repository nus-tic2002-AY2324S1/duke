package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;
import amebot.tasks.ToDo;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

/**
 * Represents a command that adds a task.
 */
public class AddCommand extends Command {
    protected Task item;

    /**
     * AddCommand constructor
     *
     * @param isMarked    Status of the task
     * @param description Description of the task
     */
    public AddCommand(boolean isMarked, String description) {
        item = new ToDo(isMarked, description);
        tasks.add(item);
        saveLogs();
    }

    /**
     * AddCommand constructor for event
     *
     * @param isMarked     Status of the task
     * @param description  Description of the task
     * @param fromDateTime Start date and time of the task
     * @param toDateTime   End date and time of the task
     */
    public AddCommand(boolean isMarked, String description, String fromDateTime, String toDateTime) {
        item = new Event(isMarked, description, fromDateTime, toDateTime);
        tasks.add(item);
        saveLogs();
    }

    /**
     * AddCommand constructor for deadline
     *
     * @param isMarked    Status of the task
     * @param description Description of the task
     * @param dueDateTime Due date and time of the task
     */
    public AddCommand(boolean isMarked, String description, String dueDateTime) {
        item = new Deadline(isMarked, description, dueDateTime);
        tasks.add(item);
        saveLogs();
    }

    /**
     * Saves the logs for output
     */
    public void saveLogs() {
        logs.add(item.getTask());
        logs.add(Messages.SUCCESS_ADD_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);
    }
}
