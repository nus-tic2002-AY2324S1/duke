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
import duke.dukeexceptions.EmptyArgumentException;
import duke.dukeexceptions.EmptyTaskListException;
import duke.dukeexceptions.EventDateException;
import duke.dukeexceptions.InvalidCommandException;
import duke.dukeexceptions.InvalidDateFormatException;
import duke.dukeexceptions.InvalidNumberFormatException;
import duke.dukeexceptions.InvalidTaskFormatException;
import duke.dukeexceptions.TaskNotFoundException;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * CommandValidator class is responsible for executing various user commands.
 */
public class CommandValidator {

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

    /**
     * Executes the specified command based on user input.
     * This method parses the command from the user input, extracts the relevant arguments,
     * and then executes the corresponding command on the task list. The supported commands
     * include listing tasks, adding todo/deadline/event tasks, finding tasks, rescheduling
     * tasks, and modifying tasks (delete/mark/unmark).
     *
     * @param fileStorage The file storage for persisting task data.
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks on which the commands will be executed.
     * @param command The parsed command from the user input.
     * @param userInput The complete user input containing the command and any additional arguments.
     * @throws DukeException If an unsupported or invalid command is provided,
     *                      or if any specific command execution encounters an exception.
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
            modifyTask(fileStorage, display, taskList, userInput);
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Executes the command to add a todo task based on the provided task name.
     * This method validates the provided task name for creating a todo task
     * and then executes the command to add the todo task to the task list.
     *
     * @param fileStorage The file storage for persisting task data.
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to which the todo task will be added.
     * @param taskName The user input specifying the todo task name.
     * @throws DukeException If an empty task name is encountered or provided for the todo command.
     */
    private void executeTodoCommand(FileStorage fileStorage, MessageDisplay display,
                                    List<Task> taskList, String taskName) throws DukeException {

        if (taskName.isEmpty()) {
            throw new EmptyArgumentException(TODO_COMMAND);
        }
        new AddTodoCommand(taskName).execute(fileStorage, display, taskList);
    }

    /**
     * Executes the command to add a deadline task based on the provided arguments.
     * This method validates the provided arguments for creating a deadline task,
     * extracts the task name and due date, and then executes the command to add
     * the deadline task to the task list.
     *
     * @param fileStorage The file storage for persisting task data.
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to which the deadline task will be added.
     * @param arguments The user input arguments specifying the deadline task details.
     * @throws DukeException If an empty argument is encountered or provided for the deadline command,
     *                      the task name or due date information is missing, an invalid date format is provided,
     *                      or the due date is in the past.
     */
    private void executeDeadlineCommand(FileStorage fileStorage, MessageDisplay display,
                                        List<Task> taskList, String arguments) throws DukeException {

        if (arguments.isEmpty()) {
            throw new EmptyArgumentException(DEADLINE_COMMAND);
        }
        String taskName =
            DukeParser.extractTaskName(arguments, DukeParser.extractDeadlineByIndex(arguments));
        String taskDueDateString = DukeParser.extractDeadlineDueDateString(arguments);
        if (taskDueDateString.isEmpty() || taskName.isEmpty()) {
            throw new InvalidTaskFormatException(DEADLINE_COMMAND);
        }
        try {
            LocalDateTime taskDueDate = DukeParser.parseDateTimeOrDate(taskDueDateString);
            if (taskDueDate.isBefore((LocalDateTime.now()))) {
                throw new DeadlineDateException();
            }
            assert taskDueDate.isAfter(LocalDateTime.now());
            new AddDeadlineCommand(taskName, taskDueDate).execute(fileStorage, display, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskFormatException(DEADLINE_COMMAND);
        } catch (AssertionError e) {
            throw new DeadlineDateException();
        }
    }

    /**
     * Executes the command to display tasks with a specified date.
     * This method validates the provided date string for the "on" command, trims it,
     * and then executes the command to display tasks with the specified date.
     *
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to search for tasks with the specified date.
     * @param arguments The user input arguments specifying the date for the "on" command.
     * @throws DukeException If an empty date string is encountered or provided for the "on" command,
     *                      or an invalid date format is provided.
     */
    private void executeOnCommand(MessageDisplay display, List<Task> taskList, String arguments)
            throws DukeException {

        String dateString = arguments.trim();
        if (dateString.isEmpty()) {
            throw new EmptyArgumentException(ON_COMMAND);
        }
        try {
            LocalDate date = DukeParser.parseDate(dateString);
            new OnCommand(date).execute(display, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Executes the command to find tasks containing a specified keyword.
     * This method validates the provided keyword for the find command, trims it,
     * and then executes the command to find and display tasks containing the specified keyword.
     *
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to search for the keyword.
     * @param arguments The user input arguments specifying the keyword for the find command.
     * @throws DukeException If an empty keyword is encountered or provided for the find command.
     */
    private void executeFindCommand(MessageDisplay display, List<Task> taskList, String arguments)
            throws DukeException {

        String keywordString = arguments.trim();
        if (keywordString.isEmpty()) {
            throw new EmptyArgumentException(FIND_COMMAND);
        }
        new FindCommand(keywordString).execute(display, taskList);
    }

    /**
     * Executes the command to add an event task based on the provided arguments.
     * This method validates the provided arguments for creating an event task, extracts
     * the task name, start date, and end date, and then executes the command to add the
     * event task to the task list.
     *
     * @param fileStorage The file storage for persisting task data.
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to which the event task will be added.
     * @param arguments The user input arguments specifying the event task details.
     * @throws DukeException If an empty argument is encountered, the task name or date
     *                      information is missing, an invalid date format is provided,
     *                      or the start date is after the end date.
     */
    private void executeEventCommand(FileStorage fileStorage, MessageDisplay display,
                                     List<Task> taskList, String arguments) throws DukeException {

        if (arguments.isEmpty()) {
            throw new EmptyArgumentException(EVENT_COMMAND);
        }
        String taskName;
        String taskFromDateString;
        String taskToDateString;
        taskFromDateString = DukeParser.extractEventFromDateString(arguments);
        taskName = DukeParser.extractTaskName(arguments, DukeParser.extractEventFromIndex(arguments));
        taskToDateString = DukeParser.extractEventToDateString(arguments);
        if (taskFromDateString.isEmpty() || taskToDateString.isEmpty() || taskName.isEmpty()) {
            throw new InvalidTaskFormatException(EVENT_COMMAND);
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
            throw new InvalidTaskFormatException(EVENT_COMMAND);
        } catch (AssertionError e) {
            throw new EventDateException();
        }
    }

    /**
     * Executes the command to list all tasks.
     * This method executes the command to list and display all tasks in the task list.
     *
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to be listed.
     */
    private void executeListCommand(MessageDisplay display, List<Task> taskList) {

        new ListCommand().execute(display, taskList);
    }

    /**
     * Reschedules a task based on the provided user input.
     * This method allows the user to reschedule a task by providing a new date and time.
     * The method validates the input and performs the rescheduling action on the task list.
     *
     * @param fileStorage The file storage for persisting task data.
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to be rescheduled.
     * @param userInput The user input specifying the task index and the new date and time.
     * @throws DukeException If an empty argument is encountered, an invalid item index,
     *                      a task not found, or an unsupported date format is provided.
     */
    private void rescheduleTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                                String userInput) throws DukeException {
        String[] input = userInput.split(" ");
        if (input.length < 2) {
            throw new EmptyArgumentException(RESCHEDULE_COMMAND);
        }
        try {
            int itemIndex = DukeParser.parseInteger(input[0]) - 1;
            LocalDateTime revisedDateTime = DukeParser.parseDateTimeOrDate(input[1]);
            // Item index must be positive and does not exceed the maximum number of tasks in the list.
            if (itemIndex < 0 || itemIndex >= taskList.size()) {
                throw new TaskNotFoundException();
            }
            if (taskList.get(itemIndex).getTaskType() == 'T') {
                throw new ChangeTodoDateException();
            }
            new RescheduleCommand(itemIndex, revisedDateTime).execute(fileStorage, display, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        } catch (AssertionError e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Modifies a task based on the provided user input.
     * This method allows the user to modify a task by marking it as completed,
     * marking it as incomplete, or deleting it from the task list. The specific
     * modification is determined by the command extracted from the user input.
     * The method validates the command and performs the corresponding action on
     * the task list.
     *
     * @param fileStorage The file storage for persisting task data.
     * @param display The message display for printing messages to the user.
     * @param taskList The list of tasks to be modified.
     * @param userInput The user input specifying the modification command and task index.
     * @throws DukeException If the task list is empty, an invalid command or
     *                      task index is provided, or an empty argument is encountered.
     */

    public void modifyTask(FileStorage fileStorage, MessageDisplay display, List<Task> taskList,
                           String userInput) throws DukeException {
        if (taskList.isEmpty()) {
            throw new EmptyTaskListException();
        }
        try {
            String command = DukeParser.parseCommandFromInput(userInput);
            int itemIndex = DukeParser.extractItemIndex(taskList, command, userInput);
            if (itemIndex == -1) {
                return;
            }
            if (itemIndex < 0 || itemIndex >= taskList.size()) {
                throw new TaskNotFoundException();
            }
            switch (command) {
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
        } catch (AssertionError e) {
            throw new TaskNotFoundException();
        }
    }

}
