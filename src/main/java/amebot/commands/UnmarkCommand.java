package amebot.commands;

import amebot.common.Messages;

/**
 * Represents a command that marks a task as not completed.
 */
public class UnmarkCommand extends Command {
    /**
     * UnmarkCommand constructor
     *
     * @param index Index of the task to be set as unmark
     */
    public UnmarkCommand(int index) {
        boolean isValidIndex = index > 0 && index <= tasks.size();

        if (isValidIndex) {
            tasks.get(index - 1).setStatusAsUnmarked();
            logs.add(Messages.STATUS_UNMARKED);

            logs.add(tasks.get(index - 1).getTask());
        }
    }
}
