package command;

import io.CrabyMessage;
import task.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * AddTaskCommand class is a class that handle the add command.
 * It implements the CommandInterface.
 * It has a method to handle the add command.
 */
public class AddTaskCommand extends CrabyMessage implements CommandInterface {
//    private static String NaturalDate(String inputDay){
//        LocalDate toDay = LocalDate.now();
//        DayOfWeek targetDay = DayOfWeek.valueOf(inputDay.toUpperCase());
//        LocalDate nextOccurrence = toDay.with(TemporalAdjusters.nextOrSame(targetDay));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
//        return nextOccurrence.format(formatter);
//    }

    /**
     * This method is to handle the add command.
     * It will add the task to the list.
     *
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        input = input.trim();
        if (input.contains("/by")) {
            String[] formatDeadline = input.split("/by");
            handleBy(input, tasks, formatDeadline);
            return 0;
        }
        if (input.contains("/from")) {
            String[] formatEvent = input.split("/from");
            handleFrom(input, tasks, formatEvent);
            return 0;
        }
        tasks.add(new Todo(input));
        printAddMessage(input, tasks);
        return 0;
    }

    private static void handleFrom(String input, List<Task> tasks, String[] formatEvent) {
        if (formatEvent.length <= 1) {
            printDateTimeParseExceptionMessage();
            return;
        }
        if (formatEvent[1].contains(("/to"))) {
            String[] timeEvent = formatEvent[1].split("/to");
            tasks.add(new Event(formatEvent[0].trim(), timeEvent[0], timeEvent[1]));
        } else {
            tasks.add(new Event(formatEvent[0].trim(), formatEvent[1]));
        }
        printAddMessage(input, tasks);
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
