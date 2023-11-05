package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkAsCompletedCommand;
import duke.command.MarkAsInCompletedCommand;
import duke.command.OnCommand;
import duke.command.RescheduleCommand;
import duke.dukeexceptions.ChangeTodoDateException;
import duke.dukeexceptions.DeadlineDateException;
import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.EmptyDeadlineArgumentException;
import duke.dukeexceptions.EmptyEventArgumentException;
import duke.dukeexceptions.EmptyFindArgumentException;
import duke.dukeexceptions.EmptyOnArgumentException;
import duke.dukeexceptions.EmptyTodoArgumentException;
import duke.dukeexceptions.EventDateException;
import duke.dukeexceptions.InvalidCommandException;
import duke.dukeexceptions.InvalidDateFormatException;
import duke.dukeexceptions.InvalidNumberFormatException;
import duke.dukeexceptions.InvalidRescheduleArgumentException;
import duke.dukeexceptions.InvalidTaskFormatException;
import duke.dukeexceptions.TaskNotFoundException;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * CommandExecutor class is responsible for executing various user commands.
 */
public class CommandExecutor {

    // Constants for command keywords
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

    /**
     * Executes the specified command with the given user input.
     *
     * @param fileStorage The file storage object for data persistence.
     * @param display     The user interface message display.
     * @param taskList    The list of tasks.
     * @param command     The command keyword.
     * @param userInput   The user's input associated with the command.
     * @throws DukeException if an error occurs during command execution.
     */
    public void executeCommand(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                               String command, String userInput) throws DukeException {

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

    /**
     * Executes a "todo" command by adding a new todo task to the task list.
     *
     * @param fileStorage The file storage object for data persistence.
     * @param display     The user interface message display.
     * @param taskList    The list of tasks.
     * @param taskName    The name of the todo task.
     * @throws DukeException if an error occurs during command execution.
     */
    private void executeTodoCommand(FileStorage fileStorage, MessageDisplay display,
                                    List<Task> taskList, String taskName) throws DukeException {

        if (taskName.isEmpty()) {
            throw new EmptyTodoArgumentException();
        }
        new AddTodoCommand(taskName).execute(fileStorage, display, taskList);
    }

    /**
     * Executes a "deadline" command by adding a new deadline task to the task list.
     *
     * @param fileStorage The file storage object for data persistence.
     * @param display     The user interface message display.
     * @param taskList    The list of tasks.
     * @param arguments   The user input arguments for the deadline task.
     * @throws DukeException if an error occurs during command execution.
     */
    private void executeDeadlineCommand(FileStorage fileStorage, MessageDisplay display,
                                        List<Task> taskList, String arguments) throws DukeException {

        if (arguments.isEmpty()) {
            throw new EmptyDeadlineArgumentException();
        }
        String taskName =
            DukeParser.extractTaskName(arguments, DukeParser.extractDeadlineByIndex(arguments));
        String taskDueDateString = DukeParser.extractDeadlineDueDateString(arguments);
        if (taskDueDateString.isEmpty()) {
            throw new InvalidTaskFormatException("deadline");
        }
        try {
            LocalDateTime taskDueDate = DukeParser.parseDateTimeOrDate(taskDueDateString);
            if (taskDueDate.isBefore((LocalDateTime.now()))) {
                throw new DeadlineDateException();
            }
            assert taskDueDate.isAfter(LocalDateTime.now());
            new AddDeadlineCommand(taskName, taskDueDate).execute(fileStorage, display, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskFormatException("deadline");
        } catch (AssertionError e) {
            throw new DeadlineDateException();
        }
    }

    /**
     * Executes an "on" command by displaying tasks for a specific date.
     *
     * @param display   The user interface message display.
     * @param taskList  The list of tasks.
     * @param arguments The user input argument specifying the date.
     * @throws DukeException if an error occurs during command execution.
     */
    private void executeOnCommand(MessageDisplay display, List<Task> taskList, String arguments)
            throws DukeException {

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

    /**
     * Executes a "find" command by searching for tasks containing a specific keyword.
     *
     * @param display   The user interface message display.
     * @param taskList  The list of tasks.
     * @param arguments The user input argument specifying the keyword to search for.
     * @throws DukeException if an error occurs during command execution.
     */
    private void executeFindCommand(MessageDisplay display, List<Task> taskList, String arguments)
            throws DukeException {

        String keywordString = arguments.trim();
        if (keywordString.isEmpty()) {
            throw new EmptyFindArgumentException();
        }
        new FindCommand(keywordString).execute(display, taskList);
    }

    /**
     * Executes an "event" command by adding a new event task to the task list.
     *
     * @param fileStorage The file storage object for data persistence.
     * @param display     The user interface message display.
     * @param taskList    The list of tasks.
     * @param arguments   The user input arguments for the event task.
     * @throws DukeException if an error occurs during command execution.
     */
    private void executeEventCommand(FileStorage fileStorage, MessageDisplay display,
                                     List<Task> taskList, String arguments) throws DukeException {

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
            if (taskFromDateTime.isAfter(taskToDateTime)) {
                throw new EventDateException();
            }
            assert taskFromDateTime.isBefore(taskToDateTime);
            new AddEventCommand(taskName, taskFromDateTime, taskToDateTime).execute(fileStorage, display,
                taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskFormatException("event");
        } catch (AssertionError e) {
            throw new EventDateException();
        }
    }

    /**
     * Executes a "list" command by displaying the list of tasks.
     *
     * @param display  The user interface message display.
     * @param taskList The list of tasks.
     */
    private void executeListCommand(MessageDisplay display, List<Task> taskList) {

        new ListCommand().execute(display, taskList);
    }

    /**
     * Reschedules a task by executing a "reschedule" command.
     *
     * @param fileStorage The file storage object for data persistence.
     * @param display     The user interface message display.
     * @param taskList    The list of tasks.
     * @param userInput   The user input for rescheduling the task.
     * @throws DukeException if an error occurs during rescheduling.
     */
    private void rescheduleTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                                String userInput) throws DukeException {

        String[] input = userInput.split(" ");
        if (input[0].isEmpty() || input[1].isEmpty()) {
            throw new InvalidRescheduleArgumentException();
        }
        try {
            int itemIndex = DukeParser.parseInteger(input[0]) - 1;
            // Item index must be positive and does not exceed the maximum number of tasks in the list.
            assert itemIndex >= 0 && itemIndex
                <
                taskList.size();
            if (taskList.get(itemIndex).getTaskType() == 'T') {
                throw new ChangeTodoDateException();
            }
            LocalDateTime revisedDateTime = DukeParser.parseDateTimeOrDate(input[1].replace("T", " "));
            new RescheduleCommand(itemIndex, revisedDateTime).execute(fileStorage, display, taskList);
        } catch (AssertionError e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Modifies a task based on the user input, executing "mark," "unmark," or "delete" commands.
     *
     * @param fileStorage The file storage object for data persistence.
     * @param display     The user interface message display.
     * @param taskList    The list of tasks.
     * @param userInput   The user input for modifying the task.
     * @throws TaskNotFoundException if the specified task is not found.
     */
    public void modifyTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                           String userInput) throws TaskNotFoundException {

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
