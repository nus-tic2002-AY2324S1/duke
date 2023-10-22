package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String EXAMPLE_USAGE = "Example of usage:\nbye";
    public ExitCommand(){}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        throwExceptionArgIsNotEmpty(keywordArgument, new ExitCommand());
        setExit();
        ui.showResponseToUser(Message.MESSAGE_GOODBYE);
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
