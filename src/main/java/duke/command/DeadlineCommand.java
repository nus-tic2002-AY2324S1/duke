package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DESC_ERR_MESSAGE = "OOPS!!! The \"description\" of a \"deadline\" cannot be empty :(";
    public static final String BY_ERR_MESSAGE = "OOPS!!! The \"/by\" of a \"deadline\" cannot be empty :(";
    public static final String EXAMPLE_USAGE = "Example of usage:\ndeadline return book /by 2/12/2019 1800\n" +
            "Date and Time format: {dd/mm/yyyy hhmm}";
    public static final Pattern BY_ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s/by\\s(?<byArgument>\\w.*)");
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if(keywordArgument.getArguments().isEmpty()){
            throw new InvalidArgumentException(Message.concat(DESC_ERR_MESSAGE,EXAMPLE_USAGE));
        }
        final Matcher byMatcher = BY_ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        if(!byMatcher.matches()){
            throw new InvalidArgumentException(Message.concat(BY_ERR_MESSAGE,EXAMPLE_USAGE));
        }
        final String description = byMatcher.group("description");
        final String byArgument = byMatcher.group("byArgument");
        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(byArgument);
        if(!dateMatcher.matches()){
            String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "date", COMMAND_WORD);
            throw new InvalidArgumentException(Message.concat(errMsg,EXAMPLE_USAGE));
        }
        final String timeArgument = dateMatcher.group("timeArgument");
        final Matcher timeMatcher = TIME_ARG_FORMAT.matcher(timeArgument);
        if(!timeMatcher.matches()){
            String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "time", COMMAND_WORD);
            throw new InvalidArgumentException(Message.concat(errMsg,EXAMPLE_USAGE));
        }
        LocalDateTime by = Parser.dateTime(dateMatcher, timeMatcher);

        Deadline deadline = new Deadline(false,description,by);
        deadline.execute();
        tasks.add(deadline);
    }
}
