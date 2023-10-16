package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(){
        setExit();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument) {
        ui.showResponseToUser(Message.MESSAGE_GOODBYE);
    }

}
