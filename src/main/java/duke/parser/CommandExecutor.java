package duke.parser;

import duke.command.*;
import duke.dukeexceptions.*;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CommandExecutor {

  public static final String LIST_COMMAND = "list";
  public static final String TODO_COMMAND = "todo";
  public static final String DEADLINE_COMMAND = "deadline";
  public static final String EVENT_COMMAND = "event";
  public static final String ON_COMMAND = "on";
  public static final String FIND_COMMAND = "find";
  public static final String RESCHEDULE_COMMAND = "reschedule";
  public static final String DELETE_COMMAND = "delete";
  public static final String MARK_COMMAND = "mark";
  public static final String UNMARK_COMMAND = "unmark";
  public static final String POSTPONE_COMMAND = "postpone";


  public void executeCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String command, String userInput) throws DukeException {

    String arguments = userInput.substring(command.length()).trim();
    switch (command) {
      case LIST_COMMAND:
        executeListCommand(display, taskList);
        break;
      case TODO_COMMAND:
        executeTodoCommand(fileStorage, display, taskList, arguments);
        break;
      case DEADLINE_COMMAND:
        executeDeadlineCommand(fileStorage, display, taskList, arguments);
        break;
      case EVENT_COMMAND:
        executeEventCommand(fileStorage, display, taskList, arguments);
        break;
      case ON_COMMAND:
        executeOnCommand(display, taskList, arguments);
        break;
      case FIND_COMMAND:
        executeFindCommand(display, taskList, arguments);
        break;
      case RESCHEDULE_COMMAND:
        rescheduleTask(fileStorage, display, taskList, arguments);
        break;
      case DELETE_COMMAND:
      case MARK_COMMAND:
      case UNMARK_COMMAND:
      case POSTPONE_COMMAND:
        modifyTask(fileStorage, display, taskList, userInput);
        break;
      default:
        throw new InvalidCommandException();
    }
  }

  private void executeTodoCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String taskName) throws DukeException {

    if (taskName.isEmpty()) {
      throw new EmptyTodoArgumentException();
    }
    new AddTodoCommand(taskName).execute(fileStorage, display, taskList);
  }

  private void executeDeadlineCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {

    if (arguments.isEmpty()) {
      throw new EmptyDeadlineArgumentException();
    }
    String taskName = DukeParser.extractTaskName(arguments, DukeParser.extractDeadlineByIndex(arguments));
    String taskDueDateString = DukeParser.extractDeadlineDueDateString(arguments);
    if (taskDueDateString.isEmpty()) {
      throw new InvalidTaskFormatException("deadline");
    }
    try {
      LocalDateTime taskDueDate = DukeParser.parseDateTimeOrDate(taskDueDateString);
      assert taskDueDate.isAfter(LocalDateTime.now());
      new AddDeadlineCommand(taskName, taskDueDate).execute(fileStorage, display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidTaskFormatException("deadline");
    } catch (AssertionError e) {
      throw new DeadlineDateException();
    }
  }

  private void executeOnCommand(MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {

    String dateString = arguments.trim();
    if (dateString.isEmpty()) {
      throw new EmptyOnArgumentException();
    }
    try {
      LocalDate date = DukeParser.parseDate(dateString);
      new OnCommand(date).execute(display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidDateFormatException();
    }
  }

  private void executeFindCommand(MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {

    String keywordString = arguments.trim();
    if (keywordString.isEmpty()) {
      throw new EmptyFindArgumentException();
    }
    new FindCommand(keywordString).execute(display, taskList);
  }

  private void executeEventCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String arguments) throws DukeException {

    if (arguments.isEmpty()) {
      throw new EmptyEventArgumentException();
    }
    String taskName;
    String taskFromDateString;
    String taskToDateString;
    taskFromDateString = DukeParser.extractEventFromDateString(arguments);
    taskName = DukeParser.extractTaskName(arguments, DukeParser.extractEventFromIndex(arguments));
    taskToDateString = DukeParser.extractEventToDateString(arguments);
    if (taskFromDateString.isEmpty() || taskToDateString.isEmpty()) {
      throw new InvalidTaskFormatException("event");
    }
    try {
      LocalDateTime taskFromDateTime = DukeParser.parseDateTimeOrDate(taskFromDateString);
      LocalDateTime taskToDateTime = DukeParser.parseDateTimeOrDate(taskToDateString);
      assert taskFromDateTime.isBefore(taskToDateTime);
      new AddEventCommand(taskName, taskFromDateTime, taskToDateTime).execute(fileStorage, display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidTaskFormatException("event");
    } catch (AssertionError e) {
      throw new EventDateException();
    }
  }

  private void executeListCommand(MessageDisplay display, List<Task> taskList) {

    new ListCommand().execute(display, taskList);
  }

  private void rescheduleTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) throws DukeException {

    String[] input = userInput.split(" ");
    if (input[0].isEmpty() || input[1].isEmpty()) {
      throw new InvalidRescheduleArgumentException();
    }
    try {
      int itemIndex = DukeParser.parseInteger(input[0]) - 1;
      assert itemIndex >= 0 && itemIndex < taskList.size(); //Item index must be positive and does not exceed than the maximum number of task in list.
      if (taskList.get(itemIndex).getTaskType() == 'T') {
        throw new ChangeTodoDateException();
      }
      LocalDateTime revisedDateTime = DukeParser.parseDateTimeOrDate(input[1].replace("T", " "));
      new RescheduleCommand(itemIndex, revisedDateTime).execute(fileStorage, display, taskList);
    } catch (AssertionError e) {
      throw new TaskNotFoundException();
    }
  }


  public void modifyTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) throws TaskNotFoundException {

    try {
      int itemIndex = DukeParser.extractItemIndex(taskList, userInput);
      if (itemIndex == -1) {
        return;
      }
      switch (DukeParser.parseCommandFromInput(userInput)) {
        case MARK_COMMAND:
          new MarkAsCompletedCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case UNMARK_COMMAND:
          new MarkAsInCompletedCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case DELETE_COMMAND:
          new DeleteCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        default:
          throw new InvalidNumberFormatException();
      }
    } catch (InvalidNumberFormatException e) {
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    } catch (DukeException e) {
      throw new TaskNotFoundException();
    }
  }

}
