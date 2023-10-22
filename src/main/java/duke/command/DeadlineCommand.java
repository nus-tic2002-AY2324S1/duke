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
    public static final String EXAMPLE_USAGE = "Example of usage:\ndeadline return book /by 2/12/2019 1800\n" +
            DATE_TIME_FORMAT_MESSAGE;
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s/by\\s(?<byArgument>\\w.*)");
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        throwExceptionArgIsEmpty(keywordArgument, new DeadlineCommand());
        final Matcher byMatcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        if(!byMatcher.matches()){
            String errMsg = String.format(SUB_ARG_ERR_MESSAGE, "/by", COMMAND_WORD);
            throw new InvalidArgumentException(Message.concat(errMsg,EXAMPLE_USAGE));
        }
        final String description = byMatcher.group("description");
        final String byArgument = byMatcher.group("byArgument");

        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(byArgument);
        throwExceptionDateIsInvalid(dateMatcher, new DeadlineCommand(), "");

        final String timeArgument = dateMatcher.group("timeArgument");

        final Matcher timeMatcher = TIME_ARG_FORMAT.matcher(timeArgument);
        throwExceptionTimeIsInvalid(timeMatcher, new DeadlineCommand(), "");

        LocalDateTime by = Parser.dateTime(dateMatcher, timeMatcher);

        Deadline deadline = new Deadline(false,description,by);
        deadline.execute();
        tasks.add(deadline);
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
