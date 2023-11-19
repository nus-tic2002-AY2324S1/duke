package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a command that lists all the tasks.
 */
public class ListCommand extends Command {
    protected ArrayList<String> logs = new ArrayList<>();

    /**
     * Returns logs for output.
     *
     * @return Logs for output.
     */
    @Override
    public ArrayList<String> executeCommand() {
        ArrayList<String> logs = new ArrayList<>();

        if (tasks.isEmpty()) {
            logs.add(Messages.EMPTY_LIST);
        }

        int itemNum = 1;

        logs.add(Messages.FULL_LIST);
        for (Task task : tasks) {
            logs.add(itemNum + ". " + task.getTask());
            itemNum++;
        }

        return logs;
    }
}
