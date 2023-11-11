package amebot.commands;

import amebot.common.Messages;

/**
 * Represents a command that marks a task as completed.
 */
public class MarkCommand extends Command {
    /**
     * MarkCommand constructor
     *
     * @param index Index of the task to be set as marked
     */
    public MarkCommand(int index) {
        boolean isValidIndex = index > 0 && index <= tasks.size();

        if (isValidIndex) {
            tasks.get(index - 1).setStatusAsMarked();
            logs.add(Messages.STATUS_MARKED);

            logs.add(tasks.get(index - 1).getTask());
        }
    }
}
