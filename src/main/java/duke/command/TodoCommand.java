package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "TODO";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws MissingDescriptionException{
        Todo todo = new Todo(keywordArgument.getArguments());
        todo.execute();
        tasks.add(todo);
    }

    public void decodeCommand(){

    }
}
