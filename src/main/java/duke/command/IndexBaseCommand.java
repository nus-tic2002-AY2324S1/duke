package duke.command;

import java.util.ArrayList;
import java.util.HashMap;

import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class IndexBaseCommand extends Command {
    private int index;

    private Command indexCommand;

    private static final HashMap<String, IIndexCommand> INDEX_COMMANDS = new HashMap<>();

    static{
        INDEX_COMMANDS.put("mark", new IIndexCommand() {
            @Override
            public Command create() {
                return new MarkCommand();
            }
        });

        INDEX_COMMANDS.put("unmark", new IIndexCommand() {
            @Override
            public Command create() {
                return new UnMarkCommand();
            }
        });

        INDEX_COMMANDS.put("delete", new IIndexCommand() {
            @Override
            public Command create() {
                return new DeleteCommand();
            }
        });
    }

    public IndexBaseCommand(){}


    private void putIndex(String input){
        index = Integer.parseInt(input);
    }

    public abstract String message();

    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument){
        String keyword = keywordArgument.getKeyword();
        try {
            indexCommand = INDEX_COMMANDS.get(keyword).create();
            putIndex(keywordArgument.getArguments());
            process(taskList, ui);
        } catch (NumberFormatException e) {
            String str = String.format("The \"index number\" of the \"%s\" must be an integer :-(", keyword);
            ui.showResponseToUser(str);
        } catch (IndexOutOfBoundsException e) {
            String str = String.format("The \"index number\" of the \"%s\" is out of range :-(", keyword);
            ui.showResponseToUser(str);
        }


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
