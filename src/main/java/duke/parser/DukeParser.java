package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.dukeexceptions.ChangeTodoDateException;
import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.EmptyArgumentException;
import duke.dukeexceptions.EmptyCommandException;
import duke.dukeexceptions.InvalidNumberFormatException;
import duke.dukeexceptions.InvalidTaskFormatException;
import duke.dukeexceptions.TaskNotFoundException;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * The `DukeParser` class is responsible for parsing user input and converting it into
 * commands and parameters for execution.
 */
public class DukeParser {

    private static final int BY_KEYWORD_LENGTH = 3;
    private static final int FROM_KEYWORD_LENGTH = 5;
    private static final int TO_KEYWORD_LENGTH = 3;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private static final DateTimeFormatter dateTimeFormatter;
    private static final DateTimeFormatter dateFormatter;

    static {
        dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    }

    private final CommandValidator commandValidator;

    /**
     * Constructs a new DukeParser object and initializes the commandValidator.
     */
    public DukeParser() {

        this.commandValidator = new CommandValidator();
    }

    /**
     * Parses a date and time string and returns a `LocalDateTime` object.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @return A `LocalDateTime` object representing the parsed date and time.
     * @throws DateTimeParseException if the string cannot be parsed as a valid date and time.
     */
    static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {

        return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
    }

    /**
     * Parses a date string and returns a `LocalDate` object.
     *
     * @param dateString The date string to be parsed.
     * @return A `LocalDate` object representing the parsed date.
     * @throws DateTimeParseException if the string cannot be parsed as a valid date.
     */
    static LocalDate parseDate(String dateString) throws DateTimeParseException {

        return LocalDate.parse(dateString, dateFormatter);
    }

    /**
     * Parses a date and time string or a date string and returns a `LocalDateTime` object.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @return A `LocalDateTime` object representing the parsed date and time.
     * @throws DateTimeParseException if the string cannot be parsed as a valid date and time.
     */
    public static LocalDateTime parseDateTimeOrDate(String dateTimeString) {

        if (dateTimeString.contains(" ")) {
            return parseDateTime(dateTimeString);
        } else {
            return parseDate(dateTimeString).atStartOfDay();
        }
    }

    /**
     * Parses an integer from a string and returns it.
     *
     * @param integer The string containing the integer.
     * @return The parsed integer.
     * @throws InvalidNumberFormatException if the string is not a valid integer.
     */
    public static Integer parseInteger(String integer) throws InvalidNumberFormatException {

        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException(e.getMessage());
        }
    }

    /**
     * Parse command from user's input string.
     *
     * @param userInput User's input string
     * @return The parsed command from the input.
     */
    protected static String parseCommandFromInput(String userInput) throws DukeException {
        int spaceIndex = userInput.indexOf(' ');
        if (spaceIndex == -1) {
            throw new EmptyArgumentException(userInput);
        }
        return userInput.substring(0, spaceIndex);
    }

    /**
     * Extracts and validates the task index from the user input.
     * This method takes a list of tasks and the user input string. It extracts the part
     * of the input string containing the task index, converts it to an integer, and
     * validates it. If the index is valid, it returns the corresponding item index in
     * the task list. If the index is out of bounds or cannot be parsed, it throws an
     * appropriate exception and prints an error message.
     *
     * @param taskList The list of tasks.
     * @param userInput The user input string containing the task index.
     * @return The validated item index in the task list.
     * @throws DukeException If the task index is out of bounds or cannot be parsed,
     *                      indicating a task not found or an invalid number format.
     */
    static int extractItemIndex(List<Task> taskList, String command , String userInput) throws DukeException {

        try {
            int spaceIndex = userInput.indexOf(' ');
            String integerPart = userInput.substring(spaceIndex + 1).trim();
            if (integerPart.isEmpty()) {
                throw new EmptyArgumentException(command);
            }
            int itemIndex = parseInteger(integerPart) - 1;
            if (itemIndex < 0 || itemIndex >= taskList.size()) {
                // Handle exception case where the item index is out of bounds or does not exist
                throw new TaskNotFoundException();
            }
            return itemIndex;
        } catch (InvalidNumberFormatException e) {
            System.out.printf("%s\n", e.getMessage());
            MessageDisplay.printLineBreak();
        }
        return -1;
    }

    /**
     * Extracts the task name from the provided arguments, up to the specified index.
     * This method takes a string of arguments and an index indicating the first occurrence
     * of a delimiter. It extracts the task name by taking a substring from the beginning
     * of the arguments up to the specified index (exclusive), and then trims any leading
     * or trailing whitespace.
     *
     * @param arguments The string containing the arguments.
     * @param firstIndex The index indicating the first occurrence of a delimiter.
     * @return The extracted task name.
     */
    static String extractTaskName(String arguments, int firstIndex) {
        return arguments.substring(0, firstIndex).trim();
    }

    /**
     * Extracts the deadline due date string from the provided arguments.
     * This method takes a string of arguments and extracts the deadline due date string
     * by locating the "/by" keyword and retrieving the substring following it. The extracted
     * string is then trimmed to remove any leading or trailing whitespace. If the resulting
     * string is empty, an exception is thrown to indicate an invalid task format.
     *
     * @param arguments The string containing the arguments.
     * @return The extracted deadline due date string.
     * @throws InvalidTaskFormatException If the deadline due date string is empty,
     *                      indicating an invalid task format.
     */
    static String extractDeadlineDueDateString(String arguments) throws DukeException {

        String taskDueDateString =
            arguments.substring(extractDeadlineByIndex(arguments) + BY_KEYWORD_LENGTH).trim();
        if (taskDueDateString.isEmpty()) {
            throw new InvalidTaskFormatException(CommandValidator.DEADLINE_COMMAND);
        }
        return taskDueDateString;
    }

    /**
     * Extracts the deadline due date string from the provided arguments.
     * This method takes a string of arguments and extracts the deadline due date string
     * by locating the "by" keyword and retrieving the substring following it. The extracted
     * string is then trimmed to remove any leading or trailing whitespace. If the resulting
     * string is empty, an exception is thrown to indicate an invalid task format.
     *
     * @param arguments The string containing the arguments.
     * @return The extracted deadline due date string.
     * @throws InvalidTaskFormatException If the deadline due date string is empty,
     *                      indicating an invalid task format.
     */
    static String extractEventFromDateString(String arguments) throws DukeException {

        int fromIndex = extractEventFromIndex(arguments);
        String taskFromDateString =
            arguments.substring(fromIndex + FROM_KEYWORD_LENGTH, extractEventToIndex(arguments)).trim();
        if (taskFromDateString.isEmpty()) {
            throw new InvalidTaskFormatException(CommandValidator.EVENT_COMMAND);
        }
        return taskFromDateString;
    }

    /**
     * Extracts the event end date string from the provided arguments.
     * This method takes a string of arguments and extracts the event end date string
     * by locating the "to" keyword and retrieving the substring following it. The extracted
     * string is then trimmed to remove any leading or trailing whitespace. If the resulting
     * string is empty, an exception is thrown to indicate an invalid task format.
     *
     * @param arguments The string containing the arguments.
     * @return The extracted event end date string.
     * @throws InvalidTaskFormatException If the event end date string is empty,
     *                      indicating an invalid task format.
     */
    static String extractEventToDateString(String arguments) throws DukeException {

        int toindex = extractEventToIndex(arguments);
        String taskToDateString = arguments.substring(toindex + TO_KEYWORD_LENGTH).trim();
        if (taskToDateString.isEmpty()) {
            throw new InvalidTaskFormatException(CommandValidator.EVENT_COMMAND);
        }
        return taskToDateString;
    }

    /**
     * Extracts the index of the "/by" keyword from the provided arguments.
     * This method searches for the position of the "/by" keyword within the given string
     * of arguments. If the keyword is found, the method returns its starting index. If not
     * found, an exception is thrown to indicate an invalid task format.
     *
     * @param arguments The string containing the arguments.
     * @return The index of the "/by" keyword in the arguments.
     * @throws InvalidTaskFormatException If the "/by" keyword is not found,
     *                      indicating an invalid task format.
     */
    static int extractDeadlineByIndex(String arguments) throws DukeException {
        int byIndex = arguments.indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidTaskFormatException(CommandValidator.DEADLINE_COMMAND);
        }
        return byIndex;
    }

    /**
     * Extracts the index of the "/from" keyword from the provided arguments.
     * This method searches for the position of the "/from" keyword within the given string
     * of arguments. If the keyword is found, the method returns its starting index. If not
     * found, an exception is thrown to indicate an invalid task format.
     *
     * @param arguments The string containing the arguments.
     * @return The index of the "/from" keyword in the arguments.
     * @throws InvalidTaskFormatException If the "/from" keyword is not found,
     *                      indicating an invalid task format.
     */
    static int extractEventFromIndex(String arguments) throws DukeException {
        int fromIndex = arguments.indexOf("/from");
        if (fromIndex == -1) {
            throw new InvalidTaskFormatException(CommandValidator.EVENT_COMMAND);
        }
        return fromIndex;
    }

    /**
     * Extracts the index of the "/to" keyword from the provided arguments.
     * This method searches for the position of the "/to" keyword within the given string
     * of arguments. If the keyword is found, the method returns its starting index. If not
     * found, an exception is thrown to indicate an invalid task format.
     *
     * @param arguments The string containing the arguments.
     * @return The index of the "/to" keyword in the arguments.
     * @throws InvalidTaskFormatException If the "/to" keyword is not found,
     *                      indicating an invalid task format.
     */
    static int extractEventToIndex(String arguments) throws DukeException {
        int toIndex = arguments.indexOf("/to");
        if (toIndex == -1) {
            throw new InvalidTaskFormatException(CommandValidator.EVENT_COMMAND);
        }
        return toIndex;
    }

    /**
     * Parse the user's input into commands and parameters for execution.
     *
     * @param fileStorage The file storage to manage tasks.
     * @param display     The message display for showing output.
     * @param taskList    The list of tasks to operate on.
     * @param userInput   The user's input string of commands.
     */
    public void parseUserInput(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                               String userInput) {

        try {
            String[] inputs = userInput.split("\\s+");
            if (inputs.length == 0 || userInput.isEmpty()) {
                throw new EmptyCommandException();
            }
            String command = inputs[0];
            commandValidator.executeCommand(fileStorage, display, taskList, command, userInput);
        } catch (InvalidNumberFormatException e) {
            // Handle the case where the task is not found by index
            System.out.printf("%s\n", e.getMessage());
            MessageDisplay.printLineBreak();
        } catch (DateTimeParseException | ChangeTodoDateException e) {
            System.out.printf("%s\n", e.getMessage());
            MessageDisplay.printLineBreak();
        } catch (DukeException e) {
            System.out.printf("%s\n", e.getMessage());
            MessageDisplay.printLineBreak();
        }
    }

}
