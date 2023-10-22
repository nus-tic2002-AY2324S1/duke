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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        throwExceptionArgIsEmpty(keywordArgument,new TodoCommand());
        Todo todo = new Todo(keywordArgument.getArguments());
        todo.execute();
        tasks.add(todo);
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
