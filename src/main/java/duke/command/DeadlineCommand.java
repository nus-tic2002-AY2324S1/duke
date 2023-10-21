package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    public static final String DESC_ERR_MESSAGE = "OOPS!!! The \"description\" of a \"deadline\" cannot be empty :(";
    public static final String BY_ERR_MESSAGE = "OOPS!!! The \"/by\" of a \"deadline\" cannot be empty :(";
    public static final String EXAMPLE_USAGE = "Example of usage:\ndeadline return book /by Sunday";
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s+/by\\s+(?<by>\\w.*)");
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        final Matcher matcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        if(keywordArgument.getArguments().isEmpty()){
            throw new InvalidArgumentException(Message.concat(DESC_ERR_MESSAGE,EXAMPLE_USAGE));
        }
        if(!matcher.matches()){
            throw new InvalidArgumentException(Message.concat(BY_ERR_MESSAGE,EXAMPLE_USAGE));
        }
        final String description = matcher.group("description");
        final String by = matcher.group("by");

        Deadline deadline = new Deadline(false,description,by);
        deadline.execute();
        tasks.add(deadline);
    }
}
