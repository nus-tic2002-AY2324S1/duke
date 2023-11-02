package command;

import io.CrabyMessage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Adds a task to the list.
 * AddTaskCommand class is a class that handle the add command.
 */
public class AddTaskCommand extends CrabyMessage implements CommandInterface {

    /**
     * Adds the task to the list.
     * It will print out the message to the user.
     * @inheritDoc
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        input = input.trim();
        if (input.contains("/by")) {
            String[] formatDeadline = input.split("/by");
            handleBy(input, tasks, formatDeadline);
            return;
        }
        if (input.contains("/from")) {
            String[] formatEvent = input.split("/from");
            handleFrom(input, tasks, formatEvent);
            return;
        }
        tasks.add(new Todo(input));
        printAddMessage(input, tasks);
    }

    private static void handleFrom(String input, List<Task> tasks, String[] formatEvent) {
        if (formatEvent.length <= 1) {
            printDateTimeParseExceptionMessage();
            return;
        }
        try {
            handleTo(tasks, formatEvent);
            printAddMessage(input, tasks);
        } catch (DateTimeException d) {
            printDateTimeParseExceptionMessage();
        }
    }

    private static void handleTo(List<Task> tasks, String[] formatEvent) {
        if (formatEvent[1].contains(("/to"))) {
            String[] timeEvent = formatEvent[1].split("/to");
            tasks.add(new Event(formatEvent[0].trim(), timeEvent[0], timeEvent[1]));
        } else {
            tasks.add(new Event(formatEvent[0].trim(), formatEvent[1]));
        }
    }

    private static void handleBy(String input, List<Task> tasks, String[] formatDeadline) {
        if (formatDeadline.length <= 1) {
            printDateTimeParseExceptionMessage();
            return;
        }
        try {
            tasks.add(new Deadline(formatDeadline[0].trim(), formatDeadline[1]));
            printAddMessage(input, tasks);
        } catch (DateTimeParseException e) {
            printDateTimeParseExceptionMessage();
        }
    }
}
