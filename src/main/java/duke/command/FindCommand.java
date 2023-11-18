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
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Searches for a keyword in task description and" +
            " displays matching tasks to the user.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Parameter: KEYWORD\n" +
            "Example of usage: " + COMMAND_WORD + " book";
    public static final String TASKS_IN_THE_LIST = "Here are the matching tasks in your list:";
    public static final String MESSAGE_LIST_IS_EMPTY = "No items match the provided search string in the list!";

    /**
     * @param taskList        The task list containing tasks to be searched.
     * @param ui              The user interface object used to display the response.*
     * @param keywordArgument The user input containing the search keyword and arguments.
     * @throws InvalidArgumentException If the search argument is empty or invalid.
     * @inheritDoc Executes a search command, finds tasks based on the provided search string, and displays the results.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new FindCommand());
        String search = keywordArgument.getArguments();
        assert !search.isEmpty() : "search string cannot be empty";
        ArrayList<Task> foundTasks = taskList.getTaskBySearchString(search);
        if (foundTasks.isEmpty()) {
            ui.showResponseToUser(MESSAGE_LIST_IS_EMPTY);
            return;
        }
        processResponseMessage(ui, foundTasks, TASKS_IN_THE_LIST);
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
