package commands;

import joshua.JoshuaUi;
import joshua.TaskList;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a schedule command.
 */
public class ScheduleCommand extends Command {

    private final String dateStr;
    private final LocalDate targetDate;

    public ScheduleCommand(String dateTimeStr) {
        int dateIdx = 0;
        dateStr = dateTimeStr.split(",")[dateIdx];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
        this.targetDate = LocalDate.parse(dateTimeStr, formatter);
    }

    /**
     * {@inheritDoc} Prints all deadlines and events that fall on the input date.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        int count = 0;

        StringBuilder tasksStr = new StringBuilder();
        for (int taskNum = 1; taskNum <= taskList.listSize(); taskNum++) {
            Task task = taskList.getTask(taskNum);
            if (task instanceof Deadline) {
                if (((Deadline) task).isDateInRange(targetDate)) {
                    count++;
                    tasksStr.append("\t").append(taskNum).append(". ").append(task).append("\n");
                }
            }
            else if (task instanceof Event) {
                if (((Event) task).isDateInRange(targetDate)) {
                    count++;
                    tasksStr.append("\t").append(taskNum).append(". ").append(task).append("\n");
                }
            }
        }

        if (count == 0) {
            ui.joshuaSays("No tasks in the schedule for " + dateStr + ".");
        }
        else {
            ui.joshuaSays("Found " + count + " task(s) in the schedule for " + dateStr + ":");
            ui.joshuaSays(tasksStr.toString());
        }
    }
}
