package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument) throws MissingDescriptionException {
        tasks.add(new Deadline(keywordArgument.getArguments()));
    }
}
