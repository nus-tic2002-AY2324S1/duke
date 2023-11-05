package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCommand extends IndexBaseCommand {
    private String description;
    private char abbreviation;
    private String key;
    private LocalDateTime by;
    private LocalDateTime from;
    private  LocalDateTime to;

    public static final String COMMAND_WORD = "update";
    public static final String EXAMPLE_USAGE = "Example of usage:\n" + COMMAND_WORD + " 1 /desc return book \t#for todo, deadline, event tasks#\n"
            + COMMAND_WORD + " 1 /by 23/02/2023 \t#only for deadline task#\n" + COMMAND_WORD + " 1 /from 23/11/2023 0300 \t#only for event task#\n"
            + COMMAND_WORD + " 1 /to 23/11/2023 0400 \t#only for event task#";
    public static final String UPDATE_ERROR_ARGUMENT = "OOPS!!! The argument format must adhere to the correct pattern!";
    public static final String UPDATE_ERROR_BY = "OOPS!!! The /by keyword is only applicable to deadline tasks!";
    public static final String UPDATE_ERROR_FROM = "OOPS!!! The /from keyword is only applicable to event tasks!";
    public static final String UPDATE_ERROR_TO = "OOPS!!! The /to keyword is only applicable to event tasks!";
    public static final String UPDATE_TASK_MESSAGE = "Wonderful! I've updated this task with the latest changes.";
    public static final String DESC = "desc";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String BY = "by";
    private static final Pattern INDEX_ARGUMENT_FORMAT = Pattern.compile("(?<index>\\d+)\\s+/(?<key>desc|by|from|to)\\s+(?<argument>\\w.*)");
    public boolean isValidArgument(UserKeywordArgument keywordArgument, TaskList taskList) throws InvalidArgumentException {
        String indexArgument = keywordArgument.getArguments();

        validateArgument(isNullOrEmpty(indexArgument));

        final Matcher argumentMatcher = INDEX_ARGUMENT_FORMAT.matcher(indexArgument);
        if(!argumentMatcher.matches()){
            throw new InvalidArgumentException(Message.concat(UPDATE_ERROR_ARGUMENT, getExampleUsage()));
        }

        setIndex(argumentMatcher.group("index"));
        key = argumentMatcher.group("key");
        String argument = argumentMatcher.group("argument");

        abbreviation = taskList.getAbbreviation(getIndex());

        switch(abbreviation){
        case 'T':
            return isValidTodoArgument(key, argument);
        case 'D':
            return isValidDeadlineArgument(key, argument);
        case 'E':
            return isValidEventArgument(key, argument);
        default:
            return false;
        }
    }

    private boolean isValidEventArgument(String key, String argument) throws InvalidArgumentException{
        if (Objects.equals(key, DESC)) {
            description = argument;
            return true;
        }
        if (Objects.equals(key, BY)) {
            throw new InvalidArgumentException(UPDATE_ERROR_BY);
        }
        final Matcher eventDateMatcher = DATE_TIME_ARG_FORMAT.matcher(argument);
        validateDateMatcher(eventDateMatcher, new UpdateCommand(), key +" in");

        final Matcher eventTimeMatcher = TIME_ARG_FORMAT.matcher(eventDateMatcher.group("timeArgument"));
        validateTimeMatcher(eventTimeMatcher, new UpdateCommand(), key + " in");

        if(Objects.equals(key, FROM)){
            from = Parser.constructDateTime(eventDateMatcher, eventTimeMatcher);
        }else{
            to = Parser.constructDateTime(eventDateMatcher, eventTimeMatcher);
        }

        return eventDateMatcher.matches();
    }

    private boolean isValidDeadlineArgument(String key, String argument) throws InvalidArgumentException{
        if (Objects.equals(key, DESC)) {
            description = argument;
            return true;
        }
        if (Objects.equals(key, FROM)) {
            throw new InvalidArgumentException(UPDATE_ERROR_FROM);
        }
        if(Objects.equals(key, TO)){
            throw new InvalidArgumentException(UPDATE_ERROR_TO);
        }
        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(argument);
        validateDateMatcher(dateMatcher,new UpdateCommand(),"");
        if(dateMatcher.matches()){
            by = Parser.constructDateTime(dateMatcher);
        }
        return dateMatcher.matches();
    }

    private boolean isValidTodoArgument(String key, String argument) throws InvalidArgumentException {
        switch(key){
        case BY:
            throw new InvalidArgumentException(UPDATE_ERROR_BY);
        case FROM:
            throw new InvalidArgumentException(UPDATE_ERROR_FROM);
        case TO:
            throw new InvalidArgumentException(UPDATE_ERROR_TO);
        default:
        }
        description = argument;
        return Objects.equals(key, DESC);
    }

    private boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if(!isValidArgument(keywordArgument, taskList)){
            return;
        }

        validateIndexRange(getIndex());

        switch(key){
        case BY:

        case FROM:
        case TO:
        default:
            taskList.updateDescription(getIndex(), description);
        }
        Command command = Parser.parseKeywordToCommand(keywordArgument);
        processCommand(taskList, ui, command);
    }

    /**
     * @return A string representing the message for a task.
     * @inheritDoc Retrieves the message indicating that a task has been updated.
     */
    @Override
    public String getMessage() {
        return UPDATE_TASK_MESSAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }
}
