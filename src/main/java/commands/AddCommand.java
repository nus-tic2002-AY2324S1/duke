package commands;

import common.Messages;
import tasks.Task;
import tasks.ToDo;
import tasks.Event;
import tasks.Deadline;

public class AddCommand extends Command {
    protected Task item;

    public AddCommand(String description) {
        item = new ToDo(description);
        tasks.add(item);
        saveLogs();
    }

    public AddCommand(String description, String fromDateTime, String toDateTime) {
        item = new Event(description, fromDateTime, toDateTime);
        tasks.add(item);
        saveLogs();
    }

    public AddCommand(String description, String dueDateTime) {
        item = new Deadline(description, dueDateTime);
        tasks.add(item);
        saveLogs();
    }

    public void saveLogs() {
        logs.add(item.getTask());
        logs.add(Messages.SUCCESS_ADD_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);
    }
}
