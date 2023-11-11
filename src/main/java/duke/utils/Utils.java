package duke.utils;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String todoPattern = "^todo (.+)$";
    public static final String deadlinePattern = "^deadline (.+) /by (.+)$";
    public static final String eventPattern = "^event (.+) /from (.+) /to (.+)$";

    static Pattern todoRegex = Pattern.compile(todoPattern);
    static Pattern deadlineRegex = Pattern.compile(deadlinePattern);
    static Pattern eventRegex = Pattern.compile(eventPattern);

    public static final String TIME_OF_BEGINNING_OF_AN_DAY = " 0000";


    /**
     * Create a new Todo task base on the provided command.
     *
     * @param fullCommand full user command for creating a Todo task.
     * @return A new Todo task.
     * @throws DukeException if the command format is invalid.
     */
    public static Todo newTodoTask(String fullCommand) throws DukeException {
        Matcher todoMatcher = todoRegex.matcher(fullCommand);
        if (!todoMatcher.matches()) {
            throw new DukeException("Invalid command, Please follow todo task command format");
        }
        return new Todo(todoMatcher.group(0));
    }

    /**
     * Create a new Deadline task base on the provided command.
     *
     * @param fullCommand full user command for creating a Todo task.
     * @return A new Deadline task.
     * @throws DukeException If the command format is invalid.
     */
    public static Deadline newDeadlineTask(String fullCommand) throws DukeException {
        Matcher deadlineMatcher = deadlineRegex.matcher(fullCommand);
        if (!deadlineMatcher.matches()) {
            throw new DukeException("Invalid command, Please follow deadline task command format");
        }
        String desc = deadlineMatcher.group(1) + " (by: " + deadlineMatcher.group(2) + ")";
        Deadline newDeadlineTask = new Deadline(desc);
        LocalDateTime deadline = Utils.parseDateTime(deadlineMatcher.group(2).trim());
        if (deadline != null) {
            String newDesc = deadlineMatcher.group(1) + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, ha")) + ")";
            return new Deadline(newDesc, deadline);
        }
        return new Deadline(desc);
    }

    /**
     * Create a new Event task base on provided command.
     *
     * @param fullCommand full user command for creating a Event task
     * @return A new Event task
     * @throws DukeException if the command format is invalid
     */
    public static Event newEventTask(String fullCommand) throws DukeException {
        Matcher eventMatcher = eventRegex.matcher(fullCommand);
        if (!eventMatcher.matches()) {
            throw new DukeException("Invalid command, Please follow event task command format");
        }
        String desc = eventMatcher.group(1) + " (from: " + eventMatcher.group(2) + " to: " + eventMatcher.group(3) + ")";
        if (isDate(eventMatcher.group(2).trim()) && isDate(eventMatcher.group(3).trim())) {
            String group2String = eventMatcher.group(2).trim() + TIME_OF_BEGINNING_OF_AN_DAY;  //if the string is date format, append beginning time of a day to it
            String group3String = eventMatcher.group(3).trim() + TIME_OF_BEGINNING_OF_AN_DAY;
            LocalDateTime start = Utils.parseDateTime(group2String);
            LocalDateTime end = Utils.parseDateTime(group3String);
            if (start != null && end != null) {
                String newDesc = eventMatcher.group(1) + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
                return new Event(newDesc, start, end);
            }
        }
        return new Event(desc);
    }

    /**
     * Parse a date and time string to a LocalDateTime object.
     *
     * @param dateTimeString date and time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        String[] dateTimePatterns = {
                "dd/MM/yyyy HHmm",
                "d/MM/yyyy HHmm",
                "d/M/yyyy HHmm",
        };
        for (String parttern : dateTimePatterns) {
            LocalDateTime dataTime = dateTimeParser(dateTimeString, parttern);
            if (dataTime != null) {
                return dataTime;
            }
        }
        return null;
    }

    /**
     * @param dateTimeString date and time string to be parsed.
     * @param parttern       parttern string
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime dateTimeParser(String dateTimeString, String parttern) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(parttern);
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks if a string is a valid date format (dd/MM/yyyy).
     *
     * @param dateString the string to be checked
     * @return true if the string is a valid date format; otherwise, false
     */
    public static boolean isDate(String dateString) {
        Pattern datePattern = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4}$");
        Matcher matcher = datePattern.matcher(dateString);
        return matcher.matches();
    }

}
