package duke.parser;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.EmptyCommandException;
import duke.dukeexceptions.InvalidNumberFormatException;
import duke.dukeexceptions.TaskNotFoundException;
import duke.dukeexceptions.InvalidTaskFormatException;
import duke.dukeexceptions.ChangeTodoDateException;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The `DukeParser` class is responsible for parsing user input and converting it into
 * commands and parameters for execution.
 */
public class DukeParser {

  private static final int BY_KEYWORD_LENGTH = 3;
  private static final int FROM_KEYWORD_LENGTH = 5;
  private static final int TO_KEYWORD_LENGTH = 3;

  private final CommandExecutor commandExecutor;

  public DukeParser() {
    this.commandExecutor = new CommandExecutor();
  }

  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
  // Date and time format for parsing
  private static final DateTimeFormatter dateTimeFormatter;
  private static final DateTimeFormatter dateFormatter;

  static {
    dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
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
   * Parse the user's input into commands and parameters for execution.
   *
   * @param fileStorage The file storage to manage tasks.
   * @param display     The message display for showing output.
   * @param taskList    The list of tasks to operate on.
   * @param userInput   The user's input string of commands.
   */
  public void parseUserInput(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) {
    try {
      String[] inputs = userInput.split("\\s+");
      if (inputs.length == 0 || userInput.isEmpty()) {
        throw new EmptyCommandException();
      }
      String command = inputs[0];
      commandExecutor.executeCommand(fileStorage, display, taskList, command, userInput);
    } catch (InvalidNumberFormatException e) {
      // Handle the case where the task is not found by index
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }catch(DateTimeParseException e){
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }catch(ChangeTodoDateException e){
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }catch (DukeException e) {
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }
  }

  /**
   * Parse command from user's input string.
   *
   * @param userInput User's input string
   * @return The parsed command from the input.
   */
  protected static String parseCommandFromInput(String userInput) {
    int spaceIndex = userInput.indexOf(' ');
    return userInput.substring(0, spaceIndex);
  }

  /**
   * Extract the item index from mark, unmark, delete commands that modify a task's status.
   *
   * @param taskList  The list of tasks.
   * @param userInput User's input string.
   * @return The extracted item index.
   * @throws InvalidNumberFormatException if the input format is incorrect.
   */
  static int extractItemIndex(List<Task> taskList, String userInput) throws DukeException{
    try {
      int spaceIndex = userInput.indexOf(' ');
      String integerPart = userInput.substring(spaceIndex + 1).trim();
      int itemIndex = parseInteger(integerPart) - 1;
      if (itemIndex < 0 || itemIndex >= taskList.size()) {
        // Handle exception case where the item index is out of bounds or does not exist
        throw new TaskNotFoundException();
      }
      return itemIndex;
    } catch (InvalidNumberFormatException e) {
      // Handle the case where the task is not found by index
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }
    return -1;
  }

  static String extractTaskName(String arguments, int firstIndex) throws DukeException {
    return arguments.substring(0, firstIndex).trim();
  }

  static String extractDeadlineDueDateString(String arguments) throws DukeException {
    String taskDueDateString = arguments.substring(extractDeadlineByIndex(arguments) + BY_KEYWORD_LENGTH).trim();
    if (taskDueDateString.isEmpty()) {
      throw new InvalidTaskFormatException("deadline");
    }
    return taskDueDateString;
  }

  static String extractEventFromDateString(String arguments) throws DukeException {
    int fromIndex = extractEventFromIndex(arguments);
    String taskFromDateString = arguments.substring(fromIndex + FROM_KEYWORD_LENGTH,extractEventToIndex(arguments)).trim();
    if (taskFromDateString.isEmpty()) {
      throw new InvalidTaskFormatException("event");
    }
    return taskFromDateString;
  }

  static String extractEventToDateString(String arguments) throws DukeException {
    int toindex = extractEventToIndex(arguments);
    String taskToDateString = arguments.substring(toindex + TO_KEYWORD_LENGTH).trim();
    if (taskToDateString.isEmpty()) {
      throw new InvalidTaskFormatException("event");
    }
    return taskToDateString;
  }

  static int extractDeadlineByIndex(String arguments) throws DukeException {
    // Find the positions of "/by"
    int byIndex = arguments.indexOf("/by");
    // Check if "/by" exists
    if (byIndex == -1) {
      throw new InvalidTaskFormatException("deadline");
    }
    return byIndex;
  }

  static int extractEventFromIndex(String arguments) throws DukeException {
    // Find the positions of "/from"
    int fromIndex = arguments.indexOf("/from");
    // Check if "/from" exists
    if (fromIndex == -1) {
      throw new InvalidTaskFormatException("event");
    }
    return fromIndex;
  }

  static int extractEventToIndex(String arguments) throws DukeException {
    // Find the positions of "/to"
    int toIndex = arguments.indexOf("/to");
    // Check if "/to" exists
    if (toIndex == -1) {
      throw new InvalidTaskFormatException("event");
    }
    return toIndex;
  }

}
