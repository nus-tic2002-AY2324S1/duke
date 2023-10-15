package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class todoCommand implements ICommand{
    @Override
    public void response() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument){
        try {
            tasks.add(new Todo(UserKeywordArgument.getArguments()));
        } catch (MissingDescriptionException e) {
            System.out.println("Error with Argument");
        }
    }
}
