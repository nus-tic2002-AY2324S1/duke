package duke.command;

import java.util.ArrayList;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Event;

/**
 * The IndexBaseCommand class serves as the base for all index-based command classes.
 */
public abstract class IndexBaseCommand extends Command {
    public static final String INDEX_INT_ERR_MESSAGE = "The \"index number\" of the \"%s\" must be an integer :(";
    public static final String OUT_OF_RANGE_ERR_MESSAGE = "The \"index number\" of the \"%s\" is out of range :(";
    private int index;
    private Command indexCommand;

    public IndexBaseCommand() {
    }

    /**
     * This abstract method returns a message associated with the implementing class. Subclasses should override this
     * method to provide specific messages.
     *
     * @return A string representing the message for a task.
     */
    public abstract String getMessage();

    public abstract String getCommandWord();

    public abstract String getExampleUsage();

    /**
     * @throws InvalidArgumentException If the input arguments are invalid or do not match the command requirements.
     * @inheritDoc Executes the command to perform an operation on a specific task in the task list.
     * Validates the command arguments, processes the command, and updates the task list accordingly.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateArgument(keywordArgument.getArguments().isEmpty());
        validateValidInteger(!Parser.isInteger(keywordArgument.getArguments()));
        setIndex(keywordArgument.getArguments());

        validateIndexRange(index);

        indexCommand = Parser.parseKeywordToCommand(keywordArgument);
        validateRecurArgument(indexCommand, taskList);
        processCommand(taskList, ui, indexCommand);
    }

    public void validateIndexRange(int ind) throws InvalidArgumentException {
        boolean isOutLowerBound = ind < 1;
        boolean isOutUpperBound = ind > TaskList.size();
        validateIndexOutOfRange(isOutLowerBound || isOutUpperBound);
    }

    /**
     * Validates the argument for the RecurCommand. Ensures that the task at the specified index is an Event.
     *
     * @param indexCommand The command object representing the action to be performed.
     * @param taskList     The list of tasks from which the task is retrieved for validation.
     * @throws InvalidArgumentException If the command is RecurCommand and the task at the specified index is not an
     *                                  Event.
     */
    private void validateRecurArgument(Command indexCommand, TaskList taskList) throws InvalidArgumentException {
        if (!(indexCommand instanceof RecurCommand)) {
            return;
        }
        Task task = taskList.get(index - 1);
        boolean isEvent = task.getAbbreviation() == 'E';
        if (!isEvent) {
            throw new InvalidArgumentException(RecurCommand.RECUR_ERROR_MESSAGE);
        }
    }

    /**
     * Validates if the index is out of the valid range and throws an exception if it is.
     *
     * @param isOutOfRange A boolean indicating if the index is out of the valid range.
     * @throws InvalidArgumentException If the index is out of the valid range.
     */
    private void validateIndexOutOfRange(boolean isOutOfRange) throws InvalidArgumentException {
        if (isOutOfRange) {
            String errorMessage = String.format(IndexBaseCommand.OUT_OF_RANGE_ERR_MESSAGE, getCommandWord());
            throw new InvalidArgumentException(Message.concat(errorMessage, getExampleUsage()));
        }
    }

    /**
     * Validates if the input is not a valid integer and throws an exception if it is not.
     *
     * @param isNotInteger A boolean indicating if the input is not a valid integer.
     * @throws InvalidArgumentException If the input is not a valid integer.
     */
    public void validateValidInteger(boolean isNotInteger) throws InvalidArgumentException {
        if (isNotInteger) {
            String errorMessage = String.format(IndexBaseCommand.INDEX_INT_ERR_MESSAGE, getCommandWord());
            throw new InvalidArgumentException(Message.concat(errorMessage, getExampleUsage()));
        }
    }

    /**
     * Validates if the argument is empty and throws an exception if it is.
     *
     * @param isArgumentEmpty A boolean indicating if the keyword is empty.
     * @throws InvalidArgumentException If the argument is empty.
     */
    public void validateArgument(boolean isArgumentEmpty) throws InvalidArgumentException {
        if (isArgumentEmpty) {
            String errorMessage = String.format(DESC_ERR_MESSAGE, getCommandWord());
            throw new InvalidArgumentException(Message.concat(errorMessage, getExampleUsage()));
        }
    }

    /**
     * Sets the index variable by parsing the input string into an integer.
     *
     * @param argument The input string to be parsed and set as the index.
     */
    public void setIndex(String argument) {
        index = Integer.parseInt(argument);
    }

    /**
     * Processes the command by performing specific operations on the task list.
     * Executes the command, updates tasks, generates response messages, and displays the response to the user.
     *
     * @param taskList The TaskList containing the tasks to be managed.
     * @param ui       The user interface for displaying messages to the user.
     */
    public void processCommand(TaskList taskList, Ui ui, Command command) {
        Task task = taskList.get(index - 1);
        ArrayList<String> messages = new ArrayList<>();
        IndexBaseCommand indexBaseCommand = (IndexBaseCommand) command;

        if (indexCommand instanceof DeleteCommand) {
            processDeleteCommand(taskList, task, indexBaseCommand);
            return;
        }
        if (indexCommand instanceof RecurCommand) {
            processRecurCommand(taskList, task, indexBaseCommand);
            return;
        }

        if (indexCommand instanceof MarkCommand) {
            processMarkCommand(task);
        }
        messages.add(indexBaseCommand.getMessage());
        messages.add(task.toString());

        ui.showResponseToUser(messages);
    }

    /**
     * Processes a MarkCommand for the given task, marking it as done or undone based on the command.
     *
     * @param task The Task to be marked as done or undone.
     */
    private void processMarkCommand(Task task) {
        MarkCommand markCommand = (MarkCommand) indexCommand;
        task.markAsDone(markCommand.isMark());
    }

    /**
     * Processes a RecurCommand for the given event task, updating its recurrence and displaying a processed task
     * response.
     *
     * @param taskList         The TaskList object containing the list of tasks.
     * @param task             The Event task to be updated for recurrence.
     * @param indexBaseCommand The IndexBaseCommand containing the index and command details.
     */
    private void processRecurCommand(TaskList taskList, Task task, IndexBaseCommand indexBaseCommand) {
        RecurCommand recurCommand = (RecurCommand) indexCommand;
        assert task instanceof Event : "The tasks must be Event!";
        recurCommand.recur(task, taskList);
        task.displayProcessedTaskResponse(indexBaseCommand.getMessage());
    }

    /**
     * Processes a DeleteCommand for the given task, removing it from the TaskList and displaying a processed task
     * response.
     *
     * @param taskList         The TaskList object containing the list of tasks.
     * @param task             The Task to be removed.
     * @param indexBaseCommand The IndexBaseCommand containing the index and command details.
     */
    private void processDeleteCommand(TaskList taskList, Task task, IndexBaseCommand indexBaseCommand) {
        removeTaskIfIsDeleteCommand(taskList);
        task.displayProcessedTaskResponse(indexBaseCommand.getMessage());
    }

    /**
     * Removes the task at the specified index from the TaskList.
     *
     * @param taskList The task list from which the task will be removed.
     */
    private void removeTaskIfIsDeleteCommand(TaskList taskList) {
        taskList.remove(index - 1);
    }

    public int getIndex() {
        return index;
    }
}
