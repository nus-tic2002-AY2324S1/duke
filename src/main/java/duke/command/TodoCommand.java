package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    public static final String ERROR_MESSAGE = "OOPS!!! The \"description\" of a \"todo\" cannot be empty :(";
    public static final String EXAMPLE_USAGE = "Example of usage:\ntodo borrow book";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if(keywordArgument.getArguments().isEmpty()){
            throw new InvalidArgumentException(Message.concat(ERROR_MESSAGE,EXAMPLE_USAGE));
        }
        Todo todo = new Todo(keywordArgument.getArguments());
        todo.execute();
        tasks.add(todo);
    }
}
