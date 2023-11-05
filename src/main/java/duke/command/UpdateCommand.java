package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCommand extends IndexBaseCommand {

    public static final String COMMAND_WORD = "update";
    public static final String EXAMPLE_USAGE = "Example of usage:\n" + COMMAND_WORD + " 1 /desc return book \t#for todo, deadline, event tasks#\n"
            + COMMAND_WORD + " 1 /by 23/02/2023 \t#only for deadline task#\n" + COMMAND_WORD + " 1 /from 23/11/2023 0300 \t#only for event task#\n"
            + COMMAND_WORD + " 1 /to 23/11/2023 0400 \t#only for event task#";
    public static final String UPDATE_ERROR_BY = "The /by keyword is only applicable to deadline tasks!";
    public static final String UPDATE_ERROR_FROM = "The /from keyword is only applicable to event tasks!";
    public static final String UPDATE_ERROR_TO = "The /to keyword is only applicable to event tasks!";
    public static final String UPDATE_TASK_MESSAGE = "Wonderful! I've updated this task with the latest changes.";
    private static final Pattern INDEX_ARGUMENT_FORMAT = Pattern.compile("(?<index>\\d+)\\s+/(?<key>desc|by|from|to)\\s+(?<argument>\\w.*)");
    public boolean isValidArgument(UserKeywordArgument keywordArgument, TaskList taskList) throws InvalidArgumentException {
        String indexArgument = keywordArgument.getArguments();

        validateArgument(isNullOrEmpty(indexArgument));


        final Matcher argumentMatcher = INDEX_ARGUMENT_FORMAT.matcher(indexArgument);

        if(!argumentMatcher.matches()){
            return false;
        }

        setIndex(argumentMatcher.group("index"));
        String key = argumentMatcher.group("key");
        String argument = argumentMatcher.group("argument");

        char abbreviation = taskList.getAbbreviation(getIndex());

        switch(abbreviation){
        case 'T':
            return isValidTodoArgument(key);
        case 'D':
            return isValidDeadlineArgument(key, argument);
        case 'E':
            return isValidEventArgument(key, argument);
        default:
            return false;
        }
    }

    private boolean isValidEventArgument(String key, String argument) {
        if (Objects.equals(key, "desc")) {
            return true;
        }
        if (!Objects.equals(key, "from") && !Objects.equals(key, "to")) {
            return false;
        }
        final Matcher dateTimeMatcher = DATE_TIME_ARG_FORMAT.matcher(argument);
        return dateTimeMatcher.matches();
    }

    private boolean isValidDeadlineArgument(String key, String argument) {
        if (Objects.equals(key, "desc")) {
            return true;
        }
        if (!Objects.equals(key, "by")) {
            return false;
        }
        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(argument);
        return dateMatcher.matches();
    }

    private boolean isValidTodoArgument(String key) {
        return Objects.equals(key, "desc");
    }

    private boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {

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
