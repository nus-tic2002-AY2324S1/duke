package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    public static final String DESC_ERR_MESSAGE = "OOPS!!! The \"description\" of a \"%s\" cannot be empty :(";
    public static final String DESC_NEED_BLANK_ERR_MESSAGE = "OOPS!!! Please leave the description blank for the \"%s\" command.";
    public static final String DATE_TIME_FORMAT_MESSAGE = "Date and Time format: {dd/mm/yyyy hhmm}";
    public static final String DATE_FORMAT_MESSAGE = "Date format: {dd/mm/yyyy}";
    public static final String SUB_ARG_ERR_MESSAGE = "OOPS!!! The \"%s\" of a \"%s\" cannot be empty :(";
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
    public void throwExceptionArgIsEmpty(UserKeywordArgument keywordArgument, Command command) throws InvalidArgumentException {
        if(keywordArgument.getArguments().isEmpty()){
            String errMsg = String.format(DESC_ERR_MESSAGE, command.getCommandWord());
            throw new InvalidArgumentException(Message.concat(errMsg,command.getExampleUsage()));
        }
    }
    public void throwExceptionArgIsNotEmpty(UserKeywordArgument keywordArgument, Command command) throws InvalidArgumentException {
        if(!keywordArgument.getArguments().isEmpty()){
            String errMsg = String.format(DESC_NEED_BLANK_ERR_MESSAGE, command.getCommandWord());
            throw new InvalidArgumentException(Message.concat(errMsg,command.getExampleUsage()));
        }
    }
    public void throwExceptionDateIsInvalid(Matcher matcher, Command command, String addOn) throws InvalidArgumentException {
        String commandWord;
        if(addOn.isEmpty()){
            commandWord = command.getCommandWord();
        }else{
            commandWord = addOn + " " + command.getCommandWord();
        }

        if(!matcher.matches()){
            String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "date", commandWord);
            throw new InvalidArgumentException(Message.concat(errMsg,command.getExampleUsage()));
        }
    }
    public void throwExceptionTimeIsInvalid(Matcher matcher, Command command, String addOn) throws InvalidArgumentException {
        String commandWord;
        if(addOn.isEmpty()){
            commandWord = command.getCommandWord();
        }else{
            commandWord = addOn + " " + command.getCommandWord();
        }

        if(!matcher.matches()){
            String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "time", commandWord);
            throw new InvalidArgumentException(Message.concat(errMsg,command.getExampleUsage()));
        }
    }
    public abstract String getExampleUsage();
    public abstract String getCommandWord();

}
