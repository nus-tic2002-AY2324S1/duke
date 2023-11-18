package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.Period;

/**
 * The RecurCommand class represents a command that sets a task at a specific index as recurring.
 */
public class RecurCommand extends IndexBaseCommand {
    private static final Period RECURRENCE = Period.ofWeeks(1);
    private static final int THREE_MONTH_IN_WEEK = 24;
    private LocalDateTime from;
    private LocalDateTime to;
    public static final String COMMAND_WORD = "recur";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Sets a recurring event to repeat every week " +
            "for three months.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Parameter : INDEX\n" +
            "Example of usage: " + COMMAND_WORD + " 1";
    public static final String RECUR_ERROR_MESSAGE = "The RECUR command is only applicable to event tasks!";
    public static final String RECURRING_EVENT_MESSAGE = "Great! I've scheduled this event to repeat every week for " +
            "the next three months.";

    /**
     * Generates recurring events based on the given event task and adds them to the task list.
     *
     * @param task     The original event task to recur.
     * @param taskList The task list to which the recurring events will be added.
     */
    public void recur(Task task, TaskList taskList) {
        Event event = (Event) task;
        String description = event.getDukeDescription();
        from = event.getFromDateTime();
        to = event.getToDateTime();
        LocalDateTime tmpFrom = from;
        LocalDateTime tmpTo = to;

        for (int i = 0; i < THREE_MONTH_IN_WEEK; i++) {
            tmpFrom = tmpFrom.plus(RECURRENCE);
            tmpTo = tmpTo.plus(RECURRENCE);
            assert !description.isEmpty() : "The description cannot be empty!";
            Event addEvent = new Event(false, description, tmpFrom, tmpTo);
            taskList.add(addEvent);
        }
    }

    /**
     * @return A string representing recurring event message.
     * @inheritDoc Retrieves the message indicating that a event has been recurring.
     */
    @Override
    public String getMessage() {
        return RECURRING_EVENT_MESSAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }
}
