package amebot.commands;

import amebot.common.Messages;
import amebot.enumerations.Keyword;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

import java.util.ArrayList;

/**
 * Represents a command that updates the description or date and time of a task.
 */
public class UpdateCommand extends Command {
    protected int index;
    protected String description;
    protected String fromDateTime;
    protected String toDateTime;
    protected String dueDateTime;
    protected ArrayList<String> logs = new ArrayList<>();

    public UpdateCommand(int index, String description, String fromDateTime, String toDateTime, String dueDateTime) {
        this.index = index;
        this.description = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Returns logs for output.
     *
     * @return logs for output.
     */
    public ArrayList<String> executeCommand() {
        String taskType = tasks.get(this.index - 1).getType().replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");
        boolean isEventType = taskType.equals(Keyword.EVENT.toString());
        boolean isFromDateTime = !this.fromDateTime.isEmpty();
        boolean isToDateTime = !this.toDateTime.isEmpty();
        boolean isDeadlineType = taskType.equals(Keyword.DEADLINE.toString());
        boolean isDueDateTime = !this.dueDateTime.isEmpty();

        if (isEventType && isFromDateTime) {
            ((Event) (tasks.get(this.index - 1))).setFromDateTime(this.fromDateTime);
            logs.add(Messages.DATE_TIME_UPDATED);
            logs.add(tasks.get(index - 1).getTask());
            return logs;
        }

        if (isEventType && isToDateTime) {
            ((Event) (tasks.get(this.index - 1))).setToDateTime(this.toDateTime);
            logs.add(Messages.DATE_TIME_UPDATED);
            logs.add(tasks.get(index - 1).getTask());
            return logs;
        }

        if (isDeadlineType && isDueDateTime) {
            ((Deadline) (tasks.get(this.index - 1))).setDueDateTime(this.dueDateTime);
            logs.add(Messages.DATE_TIME_UPDATED);
            logs.add(tasks.get(index - 1).getTask());
            return logs;
        }

        boolean isDescription = !this.description.isEmpty();

        if (isDescription) {
            tasks.get(index - 1).setDescription(this.description);
            logs.add(Messages.DESCRIPTION_UPDATED);
            logs.add(tasks.get(index - 1).getTask());
            return logs;
        }

        logs.add(Messages.INVALID_UPDATE);

        return logs;
    }
}
