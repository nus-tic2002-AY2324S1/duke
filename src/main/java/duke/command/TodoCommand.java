package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * The TodoCommand class represents a command that handles to-do tasks.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String EXAMPLE_USAGE = COMMAND_WORD + ": Adds a to-do task to the task list.\n" +
            "Parameter: DESCRIPTION\n" +
            "Example of usage: " + COMMAND_WORD + " borrow book";

    /**
     * @param tasks           The TaskList where the new Todo task will be added.
     * @param ui              The user interface for displaying messages to the user.*
     * @param keywordArgument The parsed user input containing the keyword and task description.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     * @inheritDoc Executes the Todo command to create and add a new Todo task to the task list.
     * Validates the command arguments, creates a Todo task, executes the task, and adds it to the task list.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new TodoCommand());
        Todo todo = new Todo(keywordArgument.getArguments());
        tasks.add(todo);
        todo.execute();
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
