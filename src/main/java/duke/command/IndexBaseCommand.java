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
    private int index;
    private Command indexCommand;
    /**
     * Default constructor for the IndexBaseCommand class.
     */
    public IndexBaseCommand(){}
    public abstract String getMessage();
    public abstract String getCommandWord();
    public abstract String getExampleUsage();
    /**
     * Executes the command to perform an operation on a specific task in the task list.
     * Validates the command arguments, processes the command, and updates the task list accordingly.
     * @param taskList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage object used to store and load tasks.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid or out of range, an exception is thrown with an error message.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        if(keywordArgument.getArguments().isEmpty()){
            String errorMessage = String.format(DESC_ERR_MESSAGE, getCommandWord());
            throw new InvalidArgumentException(Message.concat(errorMessage,getExampleUsage()));
        }
        if(!Parser.isInteger(keywordArgument.getArguments())){
            String errorMessage = String.format(INDEX_INT_ERR_MESSAGE, getCommandWord());
            throw new InvalidArgumentException(Message.concat(errorMessage,getExampleUsage()));
        }
        setIndex(keywordArgument.getArguments());
        if(index < 1 || index > TaskList.size()){
            String errorMessage = String.format(OUT_OF_RANGE_ERR_MESSAGE, getCommandWord());
            throw new InvalidArgumentException(Message.concat(errorMessage,getExampleUsage()));
        }
        String keyword = keywordArgument.getKeyword();
        indexCommand = Parser.parseKeywordToCommand(keyword);
        process(taskList, ui);
    }
    /**
     * Sets the index variable by parsing the input string into an integer.
     * @param input The input string to be parsed and set as the index.
     */
    private void setIndex(String input){
        index = Integer.parseInt(input);
    }
    /**
     * Processes the command by performing specific operations on the task list.
     * Executes the command, updates tasks, generates response messages, and displays the response to the user.
     * @param taskList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     */
    private void process(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> messages = new ArrayList<>();
        IndexBaseCommand indexBaseCommand = (IndexBaseCommand)indexCommand;
        messages.add(indexBaseCommand.getMessage());
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
