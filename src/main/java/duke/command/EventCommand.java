package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {
    public static final String DESC_ERR_MESSAGE = "OOPS!!! The \"description\" of a \"event\" cannot be empty :(";
    public static final String FROM_TO_ERR_MESSAGE = "OOPS!!! The \"/from & /to\" of a \"event\" cannot be empty :(";
    public static final String EXAMPLE_USAGE = "Example of usage:\nevent project meeting /from Jun 6th 2pm /to Jun 9th 4pm";
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s+/from\\s+(?<from>\\w.*)\\s+/to\\s+(?<to>\\w.*)");

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        final Matcher matcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        if(keywordArgument.getArguments().isEmpty()){
            throw new InvalidArgumentException(Message.concat(DESC_ERR_MESSAGE,EXAMPLE_USAGE));
        }
        if(!matcher.matches()){
            throw new InvalidArgumentException(Message.concat(FROM_TO_ERR_MESSAGE, EXAMPLE_USAGE));
        }
        final String description = matcher.group("description");
        final String from = matcher.group("from");
        final String to = matcher.group("to");
        Event event = new Event(false,description,from,to);
        event.execute();
        tasks.add(event);
    }
}
