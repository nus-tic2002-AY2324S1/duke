package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String EXAMPLE_USAGE = "Example of usage:\nevent project meeting /from 2/12/2020 0800 /to 2/12/2020 1000\n" +
            DATE_TIME_FORMAT_MESSAGE;
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s+/from\\s+(?<from>\\w.*)\\s+/to\\s+(?<to>\\w.*)");

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        throwExceptionArgIsEmpty(keywordArgument, new EventCommand());

        final Matcher matcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        if(!matcher.matches()){
            String errMsg = String.format(SUB_ARG_ERR_MESSAGE, "/from & /to", COMMAND_WORD);
            throw new InvalidArgumentException(Message.concat(errMsg,EXAMPLE_USAGE));
        }

        final String description = matcher.group("description");
        final String from = matcher.group("from");
        final String to = matcher.group("to");

        final Matcher fromDateMatcher = DATE_ARG_FORMAT.matcher(from);
        throwExceptionDateIsInvalid(fromDateMatcher, new EventCommand(), "'from' in");

        final Matcher toDateMatcher = DATE_ARG_FORMAT.matcher(to);
        throwExceptionDateIsInvalid(toDateMatcher, new EventCommand(), "'to' in");

        final Matcher fromTimeMatcher = TIME_ARG_FORMAT.matcher(fromDateMatcher.group("timeArgument"));
        throwExceptionTimeIsInvalid(fromTimeMatcher, new EventCommand(), "'from' in");

        final Matcher toTimeMatcher = TIME_ARG_FORMAT.matcher(toDateMatcher.group("timeArgument"));
        throwExceptionTimeIsInvalid(toTimeMatcher, new EventCommand(), "'to' in");

        LocalDateTime fromDateTime = Parser.dateTime(fromDateMatcher, fromTimeMatcher);
        LocalDateTime toDateTime = Parser.dateTime(toDateMatcher, toTimeMatcher);
        if(fromDateTime.isAfter(toDateTime)){
            String errMsg = "OOPS!!! The 'from: date/time' can not be after the 'to: date/time'";
            throw new InvalidArgumentException(Message.concat(errMsg,EXAMPLE_USAGE));
        }

        Event event = new Event(false,description,fromDateTime,toDateTime);
        event.execute();
        tasks.add(event);
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
