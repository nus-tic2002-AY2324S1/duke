package duke.parser;

import duke.command.*;
import duke.dukeexceptions.*;
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

  private static final int FROM_KEYWORD_LENGTH = 5;
  private static final int TO_KEYWORD_LENGTH = 3;
  private static final int BY_KEYWORD_LENGTH = 3;
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
   * @param display   The message display for showing output.
   * @param taskList  The list of tasks to operate on.
   * @param userInput The user's input string of commands.
   */
  public void parseUserInput(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) {

    try {
      String[] inputs = userInput.split("\\s+");
      if (inputs.length == 0 || userInput.isEmpty()) {
        throw new EmptyCommandException();
      }
      String command = inputs[0];
      executeCommand(fileStorage, display, taskList, command, userInput);
    } catch (DukeException e) {
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }
  }

  /**
   * Execute the user's command.
   *
   * @param display   The message display for showing output.
   * @param taskList  The list of tasks to operate on.
   * @param command   The parsed user command.
   * @param userInput The user's remaining string of commands excluding the command string.
   */
  private void executeCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String command, String userInput) throws DukeException {

    String arguments = userInput.substring(command.length()).trim();
    switch (command) {
      case "list":
        executeListCommand(display, taskList);
        break;
      case "todo":
        executeTodoCommand(fileStorage, display, taskList, arguments);
        break;
      case "deadline":
        executeDeadlineCommand(fileStorage, display, taskList, arguments);
        break;
      case "event":
        executeEventCommand(fileStorage, display, taskList, arguments);
        break;
      case "on":
        executeOnCommand(display, taskList, arguments);
        break;
      case "find":
        executeFindCommand(display, taskList, arguments);
        break;
      case "delete":
      case "mark":
      case "unmark":
      case "snooze":
      case "postpone":
        modifyTask(fileStorage, display, taskList, userInput);
        break;
      default:
        throw new InvalidCommandException();
    }
  }

  /**
   * Execute the user's "list" command.
   *
   * @param display  The message display for showing output.
   * @param taskList The list of tasks to operate on.
   */
  private void executeListCommand(MessageDisplay display, List<Task> taskList) {

    new ListCommand().execute(display, taskList);
  }

  /**
   * Execute the "todo" task command.
   *
   * @param display  The message display for showing output.
   * @param taskList The list of tasks to operate on.
   * @param taskName The name of the todo task.
   * @throws DukeException if there's an issue with the command or input.
   */
  private void executeTodoCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String taskName) throws DukeException {

    if (taskName.isEmpty()) {
      throw new EmptyTodoArgumentException();
    }
    new AddTodoCommand(taskName).execute(fileStorage, display, taskList);
  }

  /**
   * Execute the "deadline" task command.
   *
   * @param display   The message display for showing output.
   * @param taskList  The list of tasks to operate on.
   * @param arguments The user's input string after the deadline task command.
   * @throws DukeException if there's an issue with the command or input.
   */
  private void executeDeadlineCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {
    // Check for empty arguments
    if (arguments.isEmpty()) {
      throw new EmptyDeadlineArgumentException();
    }
    // Find the positions of "/by"
    int byIndex = arguments.indexOf("/by");
    // Check if "/by" exists
    if (byIndex == -1) {
      throw new InvalidTaskFormatException("deadline");
    }
    // Extract task name, by date
    String taskName = arguments.substring(0, byIndex).trim();
    String taskDueDateString = arguments.substring(byIndex + BY_KEYWORD_LENGTH).trim();
    // Check if date fields are empty
    if (taskDueDateString.isEmpty()) {
      throw new InvalidTaskFormatException("deadline");
    }
    try {
      // Parse date and time & Add Deadline Task
      LocalDateTime taskDueDate = parseDateTimeOrDate(taskDueDateString);
      new duke.command.AddDeadlineCommand(taskName, taskDueDate).execute(fileStorage, display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidTaskFormatException("deadline");
    }
  }

  /**
   * Execute the "event" task command.
   *
   * @param display   The message display for showing output.
   * @param taskList  The list of tasks to operate on.
   * @param arguments The user's input string after the event task command.
   * @throws DukeException if there's an issue with the command or input.
   */
  private void executeEventCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {
    // Check for empty arguments
    if (arguments.isEmpty()) {
      throw new EmptyEventArgumentException();
    }
    // Find the positions of "/from" and "/to"
    int fromIndex = arguments.indexOf("/from");
    int toIndex = arguments.indexOf("/to");
    // Check if both "/from" and "/to" exist
    if (fromIndex == -1 || toIndex == -1) {
      throw new InvalidTaskFormatException("event");
    }
    // Extract task name, from date, and to date
    String taskName;
    String taskFromDateString;
    String taskToDateString;
    taskFromDateString = arguments.substring(fromIndex + FROM_KEYWORD_LENGTH, toIndex).trim();
    taskName = arguments.substring(0, fromIndex).trim();
    taskToDateString = arguments.substring(toIndex + TO_KEYWORD_LENGTH).trim();
    // Check if date fields are empty
    if (taskFromDateString.isEmpty() || taskToDateString.isEmpty()) {
      throw new InvalidTaskFormatException("event");
    }
    try {
      LocalDateTime taskFromDateTime = parseDateTimeOrDate(taskFromDateString);
      LocalDateTime taskToDateTime = parseDateTimeOrDate(taskToDateString);
      new AddEventCommand(taskName, taskFromDateTime, taskToDateTime).execute(fileStorage, display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidTaskFormatException("event");
    }
  }

  /**
   * Execute the "On" task command.
   *
   * @param display   The message display for showing output.
   * @param taskList  The list of tasks to operate on.
   * @param arguments The user's input string after the On command.
   * @throws DukeException if there's an issue with the command or input.
   */
  private void executeOnCommand(MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {
    // Extract specified date
    String dateString = arguments.trim();
    // Check if date fields are empty
    if (dateString.isEmpty()) {
      throw new EmptyOnArgumentException();
    }
    try {
      // Parse date
      LocalDate date = parseDate(dateString);
      new OnCommand().execute(display, taskList, date);
    } catch (DateTimeParseException e) {
      throw new InvalidDateFormatException();
    }
  }

  /**
   * Executes a find command to search for tasks in the task list based on a specified keyword.
   *
   * @param display   The message display interface for showing messages to the user.
   * @param taskList  The list of tasks to search within.
   * @param arguments The search keyword to use for finding tasks.
   * @throws DukeException If the search keyword is empty, an EmptyFindArgumentException is thrown.
   */
  private void executeFindCommand(MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {
    // Extract specified search word
    String keywordString = arguments.trim();

    // Check if keyword is empty
    if (keywordString.isEmpty()) {
      throw new EmptyFindArgumentException();
    }

    // Create and execute a FindCommand to search for tasks
    new FindCommand(keywordString).execute(display, taskList);
  }


  /**
   * Execute "mark," "unmark," and "delete" commands that modify a task's status.
   *
   * @param display   The message display for showing output.
   * @param taskList  The list of tasks to operate on.
   * @param userInput User's input string after a mark, unmark, or delete command.
   */
  public void modifyTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) {

    try {
      int itemIndex = extractItemIndex(taskList, userInput);
      if (itemIndex == -1) {
        return;
      }
      switch (parseCommandFromInput(userInput)) {
        case "mark":
          new MarkAsCompletedCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case "unmark":
          new MarkAsInCompletedCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case "delete":
          new DeleteCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case "snooze":
          if (taskList.get(itemIndex).getTaskType() == 'T') {
            throw new ChangeTodoDateException();
          }
          new SnoozeCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        default:
          // Handle exception case where the command is neither mark nor unmark
          throw new InvalidNumberFormatException();
      }
    } catch (InvalidNumberFormatException e) {
      // Handle the case where the integer part is not a valid number
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    } catch (ChangeTodoDateException e) {
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
  protected String parseCommandFromInput(String userInput) {

    int spaceIndex = userInput.indexOf(' ');
    return userInput.substring(0, spaceIndex);
  }

  /**
   * Extract the item index from mark, unmark, delete commands that modify a task's status.
   *
   * @param userInput User's input string.
   * @return The extracted item index.
   * @throws InvalidNumberFormatException if the input format is incorrect.
   */
  private int extractItemIndex(List<Task> taskList, String userInput) throws InvalidNumberFormatException {

    try {
      int spaceIndex = userInput.indexOf(' ');
      String integerPart = userInput.substring(spaceIndex + 1).trim();
      int itemIndex = parseInteger(integerPart) - 1;
      if (itemIndex < 0 || itemIndex >= taskList.size()) {
        // Handle exception case where the item index is out of bounds or does not exist
        throw new TaskNotFoundException();
      }
      return itemIndex;
    } catch (TaskNotFoundException e) {
      // Handle the case where the task is not found by index
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }
    return -1;
  }

}