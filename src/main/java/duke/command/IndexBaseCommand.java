package duke.command;

import java.util.ArrayList;
import java.util.HashMap;

import duke.Main;
import duke.common.Converse;
import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class IndexBaseCommand implements ICommand {
    private int index;

    private ICommand indexCommand;

    private static final HashMap<String, IIndexCommand> INDEX_COMMANDS = new HashMap<>();

    static{
        INDEX_COMMANDS.put("mark", new IIndexCommand() {
            @Override
            public ICommand create() {
                return new Mark();
            }
        });

        INDEX_COMMANDS.put("unmark", new IIndexCommand() {
            @Override
            public ICommand create() {
                return new UnMark();
            }
        });

        INDEX_COMMANDS.put("delete", new IIndexCommand() {
            @Override
            public ICommand create() {
                return new Delete();
            }
        });
    }

    public IndexBaseCommand(){}


    protected void process(String taskDescription){
//        try {
//            indexCommand = INDEX_COMMANDS.get(getInputKey()).create();
//            putIndex(taskDescription);
//            response();
//        } catch (NumberFormatException e) {
//            String str = String.format("The \"index number\" of the \"%s\" must be an integer :-(", getInputKey());
//            Conversation.echo(str);
//        } catch (IndexOutOfBoundsException e) {
//            String str = String.format("The \"index number\" of the \"%s\" is out of range :-(", getInputKey());
//            Conversation.echo(str);
//        }
    }

    private void putIndex(String input){
        index = Integer.parseInt(input);
    }

    @Override
    public void response() {
//        ArrayList<Task> tasks = getTasks();
//        ArrayList<String> inputs = new ArrayList<>();
//        IndexBaseCommand indexBaseCommand = (IndexBaseCommand)indexCommand;
//        inputs.add(indexBaseCommand.message());
//        if(indexCommand instanceof Mark){
//            Mark markCommand = (Mark)indexCommand;
//            tasks.get(index-1).markAsDone(markCommand.isMark());
//        }
//        inputs.add(tasks.get(index-1).toString());
//        if(indexCommand instanceof Delete){
//            tasks.remove(index-1);
//        }
//        Conversation.echo(inputs);

    }

    public abstract String message();

    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument){
        String keyword = keywordArgument.getKeyword();
        try {
            indexCommand = INDEX_COMMANDS.get(keyword).create();
            putIndex(keywordArgument.getArguments());
        } catch (NumberFormatException e) {
            String str = String.format("The \"index number\" of the \"%s\" must be an integer :-(", keyword);
            ui.showResponseToUser(str);
        } catch (IndexOutOfBoundsException e) {
            String str = String.format("The \"index number\" of the \"%s\" is out of range :-(", keyword);
            ui.showResponseToUser(str);
        }

        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> messages = new ArrayList<>();
        IndexBaseCommand indexBaseCommand = (IndexBaseCommand)indexCommand;
        messages.add(indexBaseCommand.message());
        if(indexCommand instanceof Mark){
            Mark markCommand = (Mark)indexCommand;
            tasks.get(index-1).markAsDone(markCommand.isMark());
        }
        messages.add(tasks.get(index-1).toString());
        if(indexCommand instanceof Delete){
            tasks.remove(index-1);
        }
        ui.showResponseToUser(messages);
    }
}
