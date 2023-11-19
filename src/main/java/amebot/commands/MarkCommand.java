package amebot.commands;

import amebot.common.Messages;

import java.util.ArrayList;

/**
 * Represents a command that marks a task as completed.
 */
public class MarkCommand extends Command {
    protected int index;
    protected ArrayList<String> logs = new ArrayList<>();

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns logs for output.
     *
     * @return Logs for output.
     */
    @Override
    public ArrayList<String> executeCommand() {
        tasks.get(index - 1).setStatusAsMarked();
        logs.add(Messages.STATUS_MARKED);
        logs.add(tasks.get(index - 1).getTask());

        return logs;
    }
}
