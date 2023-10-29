package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * The FindCommand class is responsible for executing a command to search for tasks based on their descriptions.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String EXAMPLE_USAGE = "Example of usage:\n" + COMMAND_WORD + " book";
    public static final String TASKS_IN_THE_LIST = "Here are the matching tasks in your list:";
    public static final String MESSAGE_LIST_IS_EMPTY = "No items match the provided search string in the list!";

    /**
     * Executes a search command, finds tasks based on the provided search string, and displays the results.
     *
     * @param taskList        The task list containing tasks to be searched.
     * @param ui              The user interface object used to display the response.
     * @param storage         The storage object used for saving tasks.
     * @param keywordArgument The user input containing the search keyword and arguments.
     * @throws InvalidArgumentException If the search argument is empty or invalid.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new FindCommand());
        String search = keywordArgument.getArguments();
        ArrayList<Task> foundTasks = taskList.getTaskBySearchString(search);
        if (foundTasks.isEmpty()) {
            ui.showResponseToUser(MESSAGE_LIST_IS_EMPTY);
            return;
        }
        processResponseMessage(ui, foundTasks, TASKS_IN_THE_LIST);
    }

    /**
     * Gets the example usage string for the command.
     *
     * @return The example usage string demonstrating how to use the command.
     */
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    /**
     * Gets the command word associated with the command.
     *
     * @return The command word representing the keyword for the command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
