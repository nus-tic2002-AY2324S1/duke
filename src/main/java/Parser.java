import java.time.temporal.Temporal;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Parses user input and translates it into commands.
 * Performs sanitization and checks here
 */
public class Parser {

    String[] wordArray;

    /**
     * Parses an input to determine if it is LocalDate format, LocalDateTime, or LocalTime format
     * @param input
     * @return Temporal object of the LocalDate or LocalDateTime
     */
    private static Temporal checkTimeOrNot(String input) {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException ignored) {
            // Ignore because this means it's not LocalDateTime but LocalDate instead
        }

        try {
            return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException ignored) {
            // Ignore because this means it's not LocalTime
        }

        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return null;
        }


    }

    /**
     * Loops through the String to find the index of keywords
     *
     * @param wordArray
     * @param keyWord
     * @return The index of the keyWord
     */


    private int findIndex(String[] wordArray, String keyWord){

        int byIndex = -1;
        for (int i = 1; i < wordArray.length; i++) {
            if (wordArray[i].equals(keyWord)) {
                byIndex = i;
                break;
            }
        }
        return byIndex;

    }



    /**
     * Parses the user input and return a ParsedCommand object.
     *
     * @param userInput
     * @return A ParsedCommand object containing information about the user's command.
     */


    public ParsedCommand parseCommand(String userInput){

        wordArray = userInput.trim().split(" ");

        if(wordArray.length == 0){
            return new ParsedCommand("Invalid", "No user input");
        }

        switch(wordArray[0].toLowerCase()){
            case "list":
                return new ParsedCommand("list");
            case "bye":
                return new ParsedCommand("bye");
            case "help":
                return new ParsedCommand("help");

            case "find":
                if (wordArray.length > 1) {
                    String taskDescription = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                    return new ParsedCommand("find", taskDescription);
                } else {
                    return new ParsedCommand("invalid", "Find command missing search phrase. Usage: findphrase <search phrase>");
                }

            case "view":
                if (wordArray.length == 2) {
                    Temporal taskDate = checkTimeOrNot(wordArray[1]);
                    assert dateTime != null : "Parsing failed for input: " + input;
                    if (taskDate == null){
                        return new ParsedCommand("invalid", "Invalid date format. Usage: view <date>");
                    } else {
                        return new ParsedCommand("view", taskDate);
                    }
                } else {
                    return new ParsedCommand("invalid", "View command missing date. Usage: view <date>");
                }

            case "todo":
                if (wordArray.length > 1) {
                    String taskDescription = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                    return new ParsedCommand("todo", taskDescription);
                } else {
                    return new ParsedCommand("invalid", "Todo command missing the task name. Usage: todo <task name>");
                }

            case "deadline":

                int byIndex = findIndex(wordArray, "/by");

                if (byIndex != -1 && byIndex > 1 && byIndex < wordArray.length - 1) {
                    String taskDescription = String.join(" ", Arrays.copyOfRange(wordArray, 1, byIndex));
                    String dateTimePart = String.join(" ", Arrays.copyOfRange(wordArray, byIndex + 1, wordArray.length));
                    Temporal dateTime = checkTimeOrNot(dateTimePart);
                    assert dateTime != null : "Parsing failed for input: " + input;
                    if (dateTime != null){
                        return new ParsedCommand("deadline", taskDescription, dateTime);

                    } else{
                        return new ParsedCommand("invalid", "Invalid Date. Usage: deadline <task description> /by <date> in the format YYYY-MM-DD, /by <time> in the format HH:MM, or /by <date time> in the format YYYY-MM-DD HH:MM");
                    }

                } else {
                    return new ParsedCommand("invalid", "Deadline command is missing the task description or date/time component. Usage: deadline <task description> /by <date> in the format YYYY-MM-DD, /by <time> in the format HH:MM, or /by <date time> in the format YYYY-MM-DD HH:MM");
                }

            case "event":
                int fromIndex = findIndex(wordArray, "/from");
                int toIndex = findIndex(wordArray, "/to");

                if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex && toIndex < wordArray.length - 1) {
                    String taskDescription = String.join(" ", Arrays.copyOfRange(wordArray, 1, fromIndex));
                    String fromDateTimePart = String.join(" ", Arrays.copyOfRange(wordArray, fromIndex+1, toIndex));
                    String toDateTimePart = String.join(" ", Arrays.copyOfRange(wordArray, toIndex+1, wordArray.length));

                    Temporal fromDateTime = checkTimeOrNot(fromDateTimePart);
                    Temporal toDateTime = checkTimeOrNot(toDateTimePart);
                    assert fromDateTime != null : "Parsing failed for input: " + input;
                    assert toDateTime != null : "Parsing failed for input: " + input;
                    // Try to parse "from" and "to" date-time parts
                    if (fromDateTime == null || toDateTime == null) {

                        return new ParsedCommand("invalid", "Invalid date or date-time format for 'event' command. It should be in the yyyy-MM-dd or yyyy-MM-dd HH:mm format!");
                    } else {
                        return new ParsedCommand("event", taskDescription, fromDateTime, toDateTime);
                    }
                } else {
                    return new ParsedCommand("invalid", "Event command is missing the task description, 'from,' or 'to' components. Usage: event <task description> /from <date/time> /to <date/time>");
                }

            case "mark":
                if (wordArray.length == 2){
                    try {
                        int taskIndex = Integer.parseInt(wordArray[1]);
                        return new ParsedCommand("mark", taskIndex);
                    } catch (NumberFormatException e){
                        return new ParsedCommand("invalid", "Mark should be followed by a number!");
                    }
                } else {
                    return new ParsedCommand("invalid", "Mark command missing Task Index");
                }

            case "unmark":
                if (wordArray.length == 2){
                    try {
                        int taskIndex = Integer.parseInt(wordArray[1]);
                        return new ParsedCommand("unmark", taskIndex);
                    } catch (NumberFormatException e){
                        return new ParsedCommand("invalid", "Unmark should be followed by a number!");
                    }
                } else {
                    return new ParsedCommand("invalid", "Unmark command missing Task Index");
                }

            case "delete":
                if (wordArray.length == 2){
                    try {
                        int taskIndex = Integer.parseInt(wordArray[1]);
                        return new ParsedCommand("delete", taskIndex);
                    } catch (NumberFormatException e){
                        return new ParsedCommand("invalid", "Delete should be followed by a number!");
                    }
                } else {
                    return new ParsedCommand("invalid", "Delete command missing Task Index");
                }

            case "archive":
                if (wordArray.length == 2) {
                    String fileName = wordArray[1];
                    return new ParsedCommand("archive", fileName);
                } else {
                    return new ParsedCommand("invalid", "Archive command missing filename. Usage: archive <filename>");
                }
            default:
                return new ParsedCommand("invalid", "Could not understand your command!");
        }
    }



}
