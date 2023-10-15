package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface ICommand {
    void response();
    void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument);
}
