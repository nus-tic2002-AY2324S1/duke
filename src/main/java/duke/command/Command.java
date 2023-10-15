package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    private boolean isExit = false;
    public abstract void  execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws MissingDescriptionException;

    protected void setExit(boolean isExit){
        this.isExit = isExit;
    }

    public  boolean isExit(){
        return isExit;
    }
}
