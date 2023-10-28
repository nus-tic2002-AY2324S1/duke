package amebot.commands;

import amebot.common.Messages;
import amebot.common.Regex;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

import java.util.ArrayList;

/**
 * UpdateCommand class
 *
 * <p>Command to update the status of a task
 */
public class UpdateCommand extends Command {
    /**
     * UpdateCommand constructor
     *
     * @param index         index of the task to be updated
     * @param parsedCommand parsed command from the user input
     */
    public UpdateCommand(int index, ArrayList<String> parsedCommand) {
        boolean isValidIndex = index > 0 && index <= tasks.size();

        if (isValidIndex) {
            setDescriptionOrDateTime(index, parsedCommand);

            logs.add(tasks.get(index - 1).getTask());
        }
    }

    /**
     * Sets the description or date and time of the task
     *
     * @param index         index of the task to be updated
     * @param parsedCommand parsed command from the user input
     */
    public void setDescriptionOrDateTime(int index, ArrayList<String> parsedCommand) {
        String content = parsedCommand.get(2).toUpperCase();

        if (content.contains(Regex.UPDATE_FROM_PATTERN)) {
            ((Event) (tasks.get(index - 1))).setFromDateTime(content);

            String toDateTime = parsedCommand.get(3).toUpperCase();
            ((Event) (tasks.get(index - 1))).setToDateTime(toDateTime);
            logs.add(Messages.DATETIME_UPDATED);
        } else if (content.contains(Regex.UPDATE_DUE_PATTERN)) {
            ((Deadline) (tasks.get(index - 1))).setDueDateTime(content);
            logs.add(Messages.DATETIME_UPDATED);
        } else {
            tasks.get(index - 1).setDescription(content);
            logs.add(Messages.DESCRIPTION_UPDATED);
        }
    }
}
