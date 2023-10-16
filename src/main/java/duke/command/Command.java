package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    private static boolean isExit = false;
    public abstract void  execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws MissingDescriptionException;

    protected static void setExit(){
        isExit = true;
    }

    public static boolean isExit(){
        return isExit;
    }
}
