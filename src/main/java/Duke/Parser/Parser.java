package Duke.Parser;

import Duke.Command.*;
import Duke.DukeExceptions.*;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

public class Parser {

    /**
     * Parse the user's input into commands and parameters for execution
     *
     * @param userInput The user's input string of commands.
     */
    public void parse(String userInput) {
        try {
            String[] inputs = userInput.split("\\s+");
            if (inputs.length == 0 || userInput.isEmpty()) {
                throw new EmptyCommandException();
            }
            String command = inputs[0];
            executeCommand(command, userInput);
        } catch (DukeException e) {
            System.out.printf("%s\n%s\n", e.getMessage(), UserInterface.MessageDisplay.LINE_BREAK);
        }
    }

    /**
     * Execute the user's command
     *
     * @param command The parsed user command
     * @param userInput The user's remaining string of commands excluding command string.
     */
    private void executeCommand(String command, String userInput) throws DukeException {
        String arguments = userInput.substring(command.length()).trim();
        switch (command) {
            case "list":
                executeListCommand();
                break;
            case "todo":
                executeTodoCommand(arguments);
                break;
            case "deadline":
                executeDeadlineCommand(arguments);
                break;
            case "event":
                executeEventCommand(arguments);
                break;
            case "delete":
            case "mark":
            case "unmark":
                modifyTask(userInput);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Execute the user's command
     */
    private void executeListCommand() {
        new ListCommand().execute();
    }

    /**
     * Execute the to-do task command
     * @param taskName Duke.Task.Task name of the to-do task
     */
    private void executeTodoCommand(String taskName) throws DukeException {
        if (taskName.isEmpty()) {
            throw new EmptyTodoArgumentException();
        }
        new AddTodoCommand(taskName).execute();
    }

    /**
     * Execute the deadline task command
     * @param arguments User's input string after deadline task command
     */
    private void executeDeadlineCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new EmptyDeadlineArgumentException();
        }
        if (!arguments.contains("/by")) {
            throw new InvalidTaskFormatException("deadline");
        }
        int byIndex = arguments.indexOf("/by");
        String taskName = arguments.substring(0, byIndex).trim();
        String taskDueDate = arguments.substring(byIndex + 3).trim();
        if (taskDueDate.isEmpty()) {
            throw new InvalidTaskFormatException("deadline");
        }
        new AddDeadLineCommand(taskName, taskDueDate).execute();
    }

    /**
     * Execute the event task command
     * @param arguments User's input string after event task command
     */
    private void executeEventCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new EmptyEventArgumentException();
        }
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new InvalidTaskFormatException("event");
        }
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        String taskName = arguments.substring(0, fromIndex).trim();
        String taskFromDate = arguments.substring(fromIndex + 5, toIndex).trim();
        String taskToDate = arguments.substring(toIndex + 3).trim();
        if (taskFromDate.isEmpty() || taskToDate.isEmpty()) {
            throw new InvalidTaskFormatException("event");
        }
        new AddEventCommand(taskName, taskFromDate, taskToDate).execute();
    }

    /**
     * Execute mark, unmark, delete commands that modify a task's status
     * @param userInput User's input string after mark, unmark, delete command
     */
    public void modifyTask(String userInput) {
        try {
            int itemIndex = extractItemIndex(userInput);
            if (itemIndex == -1) {
                return;
            }
            switch (parseCommandFromInput(userInput)) {
                case "mark":
                    new MarkAsCompletedCommand(itemIndex).execute();
                    break;
                case "unmark":
                    new MarkAsInCompletedCommand(itemIndex).execute();
                    break;
                case "delete":
                    new DeleteCommand(itemIndex).execute();
                    break;
                default:
                    // Handle exception case where the command is neither mark nor unmark
                    throw new InvalidNumberFormatException();
            }
        } catch (InvalidNumberFormatException e) {
            // Handle the case where the integer part is not a valid number
            System.out.printf("%s\n%s\n", e.getMessage(), UserInterface.MessageDisplay.LINE_BREAK);
        }
    }

    /**
     * Parse command from user's input string
     * @param userInput User's input string
     */
    private String parseCommandFromInput(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        return userInput.substring(0, spaceIndex);
    }

    /**
     * Parse item index from mark, unmark, delete commands that modify a task's status
     * @param userInput User's input string
     */
    private int extractItemIndex(String userInput) throws InvalidNumberFormatException {
        try {
            int spaceIndex = userInput.indexOf(' ');
            String integerPart = userInput.substring(spaceIndex + 1).trim();
            int itemIndex = Integer.parseInt(integerPart) - 1;
            if (itemIndex < 0 || itemIndex >= Task.getTotalTasks()) {
                // Handle exception case where the item index is out of bounds or does not exist
                throw new TaskNotFoundException();
            }
            return itemIndex;
        } catch (TaskNotFoundException e) {
            // Handle the case where task is not found from index
            System.out.printf("%s\n%s\n", e.getMessage(), UserInterface.MessageDisplay.LINE_BREAK);
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException(e.getMessage());
        }
        return -1;
    }
}
