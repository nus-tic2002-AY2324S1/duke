package amebot.commands;

import amebot.common.Messages;

import java.util.ArrayList;

/**
 * Represents a command that marks a task as not completed.
 */
public class UnmarkCommand extends Command {
    protected int index;
    protected ArrayList<String> logs = new ArrayList<>();

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as not completed.
     *
     * @return Logs for output.
     */
    public ArrayList<String> executeCommand() {
        tasks.get(index - 1).setStatusAsUnmarked();
        logs.add(Messages.STATUS_UNMARKED);
        logs.add(tasks.get(index - 1).getTask());

        return logs;
    }
}
