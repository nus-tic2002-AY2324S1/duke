package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;
import amebot.tasks.ToDo;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

import java.util.ArrayList;

/**
 * Represents a command that adds a task.
 */
public class AddCommand extends Command {
    protected Task item;
    protected ArrayList<String> logs = new ArrayList<>();

    public AddCommand(boolean isStatusMarked, String description) {
        item = new ToDo(isStatusMarked, description);
        tasks.add(item);
    }

    public AddCommand(boolean isStatusMarked, String description, String fromDateTime, String toDateTime) {
        item = new Event(isStatusMarked, description, fromDateTime, toDateTime);
        tasks.add(item);
    }

    public AddCommand(boolean isStatusMarked, String description, String dueDateTime) {
        item = new Deadline(isStatusMarked, description, dueDateTime);
        tasks.add(item);
    }

    /**
     * Returns list of tasks.
     *
     * @return list of tasks.
     */
    public ArrayList<String> executeCommand() {
        logs.add(item.getTask());
        logs.add(Messages.SUCCESS_ADD_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);

        return logs;
    }
}
