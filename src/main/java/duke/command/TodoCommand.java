package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String EXAMPLE_USAGE = "Example of usage:\ntodo borrow book";

    /**
     * Executes the Todo command to create and add a new Todo task to the task list.
     * Validates the command arguments, creates a Todo task, executes the task, and adds it to the task list.
     *
     * @param tasks           The TaskList where the new Todo task will be added.
     * @param ui              The user interface for displaying messages to the user.
     * @param storage         The storage object used to store and load tasks
     * @param keywordArgument The parsed user input containing the keyword and task description.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     * message.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new TodoCommand());
        Todo todo = new Todo(keywordArgument.getArguments());
        todo.execute();
        tasks.add(todo);
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
