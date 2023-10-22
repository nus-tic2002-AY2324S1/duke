package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    public static final String DATE_TIME_ERR_MESSAGE = "OOPS!!! The %s format of a \"%s\" is invalid :(";
    public static final Pattern DATE_ARG_FORMAT = Pattern.compile("(?<day>[0-9]+)/" +
            "(?<month>[0-9]+)/(?<year>[0-9]{4})\\s(?<timeArgument>\\w.*)");
    public static final Pattern TIME_ARG_FORMAT = Pattern.compile("(?<hour>[0-9]{2})(?<minute>[0-9]{2})");
    private static boolean isExit = false;
    public abstract void  execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException;

    protected static void setExit(){
        isExit = true;
    }

    public static boolean isExit(){
        return isExit;
    }

}
