package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "EVENT";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws MissingDescriptionException {
        tasks.add(new Event(keywordArgument.getArguments()));
    }
}
