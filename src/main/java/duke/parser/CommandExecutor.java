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

  public void executeCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String command, String userInput) throws DukeException {
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
      case "reschedule":
        rescheduleTask(fileStorage, display, taskList, arguments);
        break;
      case "delete":
      case "mark":
      case "unmark":
      case "postpone":
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
      new AddDeadlineCommand(taskName, taskDueDate).execute(fileStorage, display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidTaskFormatException("deadline");
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
      new AddEventCommand(taskName, taskFromDateTime, taskToDateTime).execute(fileStorage, display, taskList);
    } catch (DateTimeParseException e) {
      throw new InvalidTaskFormatException("event");
    }
  }

  private void executeListCommand(MessageDisplay display, List<Task> taskList) {
    new ListCommand().execute(display, taskList);
  }

  private void rescheduleTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) throws DukeException {
    String[] input = userInput.split(" ");
    int itemIndex= DukeParser.parseInteger(input[0]) - 1;
    if(itemIndex< 0 || itemIndex >= taskList.size()){
      throw new TaskNotFoundException();
    }
    LocalDateTime revisedDateTime = DukeParser.parseDateTimeOrDate(input[1].replace("T"," "));
    if (taskList.get(itemIndex).getTaskType() == 'T') {
      throw new ChangeTodoDateException();
    }
    new RescheduleCommand(itemIndex,revisedDateTime).execute(fileStorage, display, taskList);
  }


  public void modifyTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList, String userInput) throws TaskNotFoundException {
    try {
      int itemIndex = DukeParser.extractItemIndex(taskList, userInput);
      if (itemIndex == -1) {
        return;
      }
      switch (DukeParser.parseCommandFromInput(userInput)) {
        case "mark":
          new MarkAsCompletedCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case "unmark":
          new MarkAsInCompletedCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        case "delete":
          new DeleteCommand(itemIndex).execute(fileStorage, display, taskList);
          break;
        default:
          throw new InvalidNumberFormatException();
      }
    } catch (InvalidNumberFormatException e) {
      System.out.printf("%s\n", e.getMessage());
      MessageDisplay.printLineBreak();
    }catch(DukeException e){
      throw new TaskNotFoundException();
    }
  }

}
