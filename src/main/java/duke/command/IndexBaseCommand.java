package duke.command;

import java.util.ArrayList;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class IndexBaseCommand extends Command {
    public static final String DESC_ERR_MESSAGE = "OOPS!!! The \"description\" of a \"%s\" cannot be empty :(";
    public static final String INDEX_INT_ERR_MESSAGE = "The \"index number\" of the \"%s\" must be an integer :(";
    public static final String OUT_OF_RANGE_ERR_MESSAGE = "The \"index number\" of the \"%s\" is out of range :(";
    public static final String EXAMPLE_USAGE = "Example of usage:\n%s";
    private int index;
    private Command indexCommand;
    public IndexBaseCommand(){}
    private void setIndex(String input){
        index = Integer.parseInt(input);
    }
    public abstract String message();
    public abstract String getCommandWord();
    public abstract String getExampleUsage();
    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if(keywordArgument.getArguments().isEmpty()){
            String errorMessage = String.format(DESC_ERR_MESSAGE, getCommandWord());
            String usageMessage = String.format(EXAMPLE_USAGE, getExampleUsage());
            throw new InvalidArgumentException(Message.concat(errorMessage,usageMessage));
        }
        if(!Parser.isInteger(keywordArgument.getArguments())){
            String errorMessage = String.format(INDEX_INT_ERR_MESSAGE, getCommandWord());
            String usageMessage = String.format(EXAMPLE_USAGE, getExampleUsage());
            throw new InvalidArgumentException(Message.concat(errorMessage,usageMessage));
        }
        setIndex(keywordArgument.getArguments());
        if(index < 1 || index > TaskList.size()){
            String errorMessage = String.format(OUT_OF_RANGE_ERR_MESSAGE, getCommandWord());
            String usageMessage = String.format(EXAMPLE_USAGE, getExampleUsage());
            throw new InvalidArgumentException(Message.concat(errorMessage,usageMessage));
        }
        String keyword = keywordArgument.getKeyword();
        indexCommand = Parser.parse(keyword);
        process(taskList, ui);
    }

    private void process(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> messages = new ArrayList<>();
        IndexBaseCommand indexBaseCommand = (IndexBaseCommand)indexCommand;
        messages.add(indexBaseCommand.message());
        if(indexCommand instanceof MarkCommand){
            MarkCommand markCommand = (MarkCommand)indexCommand;
            tasks.get(index-1).markAsDone(markCommand.isMark());
        }
        messages.add(tasks.get(index-1).toString());
        if(indexCommand instanceof DeleteCommand){
            tasks.remove(index-1);
        }
        ui.showResponseToUser(messages);
    }
}
