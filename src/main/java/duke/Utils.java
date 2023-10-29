package duke;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Todos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String todoPattern = "^todo (.+)$";
    public static final String deadlinePattern = "^deadline (.+) /by (.+)$";
    public static final String eventPattern =  "^event (.+) /from (.+) /to (.+)$";

    static Pattern todoRegex = Pattern.compile(todoPattern);
    static Pattern deadlineRegex = Pattern.compile(deadlinePattern);
    static Pattern eventRegex = Pattern.compile(eventPattern);

    public static final String TIME_OF_BEGINNING_OF_AN_DAY = " 0000";


    public static Todos newTodoTask(String fullCommand) throws DukeException {
        Matcher todoMatcher = todoRegex.matcher(fullCommand);
        if(!todoMatcher.matches()){
            throw new DukeException("Invalid command, Please follow todo task command format");
        }
        return new Todos(todoMatcher.group(0));
    }

    public static Deadlines newDeadlineTask(String fullCommand) throws DukeException {
        Matcher deadlineMatcher = deadlineRegex.matcher(fullCommand);
        if(!deadlineMatcher.matches()){
            throw new DukeException("Invalid command, Please follow deadline task command format");
        }
        String desc = deadlineMatcher.group(1) + " (by: " +deadlineMatcher.group(2) + ")";
        Deadlines newDeadlineTask = new Deadlines(desc);
        LocalDateTime deadline = Utils.parseDateTime(deadlineMatcher.group(2).trim());
        if (deadline != null){
            String newDesc = deadlineMatcher.group(1) + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, ha")) + ")";
            return new Deadlines(newDesc, deadline);
        }
        return new Deadlines(desc);
    }

    public static Events newEventTask(String fullCommand) throws DukeException {
        Matcher eventMatcher = eventRegex.matcher(fullCommand);
        if(!eventMatcher.matches()){
            throw new DukeException("Invalid command, Please follow event task command format");
        }
        String desc = eventMatcher.group(1) + " (from: " + eventMatcher.group(2) + " to: " + eventMatcher.group(3) + ")";
        if (isDate(eventMatcher.group(2).trim()) && isDate(eventMatcher.group(3).trim())){
            String group2String = eventMatcher.group(2).trim() + TIME_OF_BEGINNING_OF_AN_DAY;  //if the string is date format, append beginning time of a day to it
            String group3String = eventMatcher.group(3).trim() + TIME_OF_BEGINNING_OF_AN_DAY;
            LocalDateTime start = Utils.parseDateTime(group2String);
            LocalDateTime end = Utils.parseDateTime(group3String);
            if (start != null && end != null ){
                String newDesc = eventMatcher.group(1) + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
                return new Events(newDesc, start, end);
            }
        }
        return new Events(desc);
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        String[]dateTimePatterns = {
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

    public static LocalDateTime dateTimeParser(String dateTimeString, String parttern){
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(parttern);
            return LocalDateTime.parse(dateTimeString,dateTimeFormatter);
        }catch (DateTimeParseException e){
            return null;
        }
    }

    public static boolean isDate(String dateString){
        Pattern datePattern = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4}$");
        Matcher matcher = datePattern.matcher(dateString);
        return matcher.matches();
    }

}
