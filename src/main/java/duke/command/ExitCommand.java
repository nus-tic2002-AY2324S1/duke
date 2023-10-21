package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String ERROR_MESSAGE = "OOPS!!! If you want to say goodbye, please leave the description blank.";
    public static final String EXAMPLE_USAGE = "Example of usage:\nbye";
    public ExitCommand(){}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if(!keywordArgument.getArguments().isEmpty()){
            throw new InvalidArgumentException(Message.concat(ERROR_MESSAGE,EXAMPLE_USAGE));
        }
        setExit();
        ui.showResponseToUser(Message.MESSAGE_GOODBYE);
    }

}
