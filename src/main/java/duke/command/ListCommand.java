package duke.command;

import java.util.ArrayList;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String EXAMPLE_USAGE = "Example of usage:\nlist";
    public static final String TASKS_IN_THE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_LIST_IS_EMPTY = "Your list is empty. Let's start adding some items! :)";

    /**
     * Default constructor for the List class.
     */
    public ListCommand() {
    }

    /**
     * Executes the List command to display the tasks in the task list to the user.
     * Validates the command arguments and shows the list of tasks to the user through the user interface.
     *
     * @param taskList        The TaskList containing the tasks to be displayed.
     * @param ui              The user interface for displaying messages and tasks to the user.
     * @param storage         The storage object used to store and load tasks.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     * message.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateNonEmptyKeywordArgument(keywordArgument, new ListCommand());
        if (TaskList.size() == 0) {
            ui.showResponseToUser(MESSAGE_LIST_IS_EMPTY);
            return;
        }
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> messages = new ArrayList<>();
        messages.add(TASKS_IN_THE_LIST);
        for (int i = 0; i <= tasks.size() - 1; i++) {
            messages.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
        ui.showResponseToUser(messages);
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
