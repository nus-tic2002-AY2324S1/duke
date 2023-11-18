package duke.storage;

import duke.command.Command;
import duke.common.Message;
import duke.exception.FileStorageException;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

/**
 * The TaskDecoder class is responsible for decoding tasks from a specific format.
 * It provides methods for converting tasks from a stored format into Task objects that can be used by the application.
 */
public class TaskDecoder {
    /**
     * Decodes a list of strings into Task objects and returns them as an ArrayList.
     *
     * @param strings The list of strings representing tasks to be decoded.
     * @return An ArrayList containing Task objects decoded from the input strings.
     * @throws FileStorageException     If there are issues with decoding the tasks.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    public static ArrayList<Task> decodeStringsToTaskList(List<String> strings) throws FileStorageException,
            InvalidArgumentException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String string : strings) {
            tasks.add(decodeStringToTask(string));
        }
        return tasks;
    }

    /**
     * Decodes a string representation of a task and returns the corresponding Task object.
     *
     * @param string The string representation of the task to be decoded.
     * @return The Task object decoded from the input string.
     * @throws FileStorageException     If there are issues with the format or parsing of the string.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    private static Task decodeStringToTask(String string) throws FileStorageException, InvalidArgumentException {
        char abbreviation = string.charAt(0);
        String[] tokens = splitStringByDelimiter(string);
        switch (abbreviation) {
        case 'T':
            if (tokens.length != 3) {
                break;
            }
            return new Todo(Parser.parseStringToBoolean(tokens[1]), tokens[2]);
        case 'D':
            if (tokens.length != 4) {
                break;
            }
            LocalDateTime dateTime = parseStringToDate(tokens[3]);
            return new Deadline(Parser.parseStringToBoolean(tokens[1]), tokens[2], dateTime);
        case 'E':
            if (tokens.length != 5) {
                break;
            }
            LocalDateTime fromDateTime = parseStringToDateTime(tokens[3]);
            LocalDateTime toDateTime = parseStringToDateTime(tokens[4]);
            if (fromDateTime == null || toDateTime == null) {
                break;
            }
            return new Event(Parser.parseStringToBoolean(tokens[1]), tokens[2], fromDateTime, toDateTime);
        default:
        }
        String message = Message.concat(Message.MESSAGE_FILE_FORMAT_INVALID,
                Message.MESSAGE_MAKE_NEW_INSTANCE);
        throw new FileStorageException(message);
    }

    /**
     * Splits the input string into tokens using the "|" delimiter and returns the tokens as an array.
     *
     * @param string The input string to be split into tokens.
     * @return An array of strings representing the tokens extracted from the input string.
     */
    private static String[] splitStringByDelimiter(String string) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
        while (stringTokenizer.hasMoreTokens()) {
            tokens.add(stringTokenizer.nextToken());
        }
        String[] arr = new String[tokens.size()];
        return tokens.toArray(arr);
    }

    /**
     * Parses a string argument representing a date and time and returns a LocalDateTime object.
     *
     * @param byArgument The string argument representing the date and time.
     * @return The LocalDateTime object parsed from the input string, or null if parsing fails.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    private static LocalDateTime parseStringToDateTime(String byArgument) throws InvalidArgumentException {
        final Matcher dateMatcher = Command.DATE_TIME_ARG_FORMAT.matcher(byArgument);
        if (!dateMatcher.matches()) {
            return null;
        }
        final String timeArgument = dateMatcher.group("timeArgument");
        final Matcher timeMatcher = Command.TIME_ARG_FORMAT.matcher(timeArgument);
        if (!timeMatcher.matches()) {
            return null;
        }
        return Parser.constructDateTime(dateMatcher, timeMatcher);
    }

    private static LocalDateTime parseStringToDate(String byArgument) throws InvalidArgumentException {
        final Matcher dateMatcher = Command.DATE_ARG_FORMAT.matcher(byArgument);
        if (!dateMatcher.matches()) {
            return null;
        }

        return Parser.constructDateTime(dateMatcher);
    }
}
