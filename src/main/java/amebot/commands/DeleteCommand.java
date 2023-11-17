package amebot.commands;

import amebot.common.Messages;

import java.util.ArrayList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    protected int index;
    protected ArrayList<String> logs = new ArrayList<>();

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns logs of removed task.
     *
     * @return Logs for output.
     */
    public ArrayList<String> executeCommand() {
        logs.add(tasks.get(index - 1).getTask());
        tasks.remove(index - 1);
        logs.add(Messages.SUCCESS_REMOVE_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);

        return logs;
    }
}
