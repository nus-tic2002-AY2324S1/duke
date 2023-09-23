import java.util.ArrayList;
import java.util.HashMap;

public abstract class IndexBaseCommand extends Duke implements ICommand{
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
        try {
            indexCommand = INDEX_COMMANDS.get(getInputKey()).create();
            putIndex(taskDescription);
            response();
        } catch (NumberFormatException e) {
            String str = String.format("The \"index number\" of the \"%s\" must be an integer :-(", getInputKey());
            Conversation.echo(str);
        } catch (IndexOutOfBoundsException e) {
            String str = String.format("The \"index number\" of the \"%s\" is out of range :-(", getInputKey());
            Conversation.echo(str);
        }
    }

    private void putIndex(String input){
        index = Integer.parseInt(input);
    }

    @Override
    public void response() {
        ArrayList<Task> tasks = getTasks();
        ArrayList<String> inputs = new ArrayList<>();
        IndexBaseCommand indexBaseCommand = (IndexBaseCommand)indexCommand;
        inputs.add(indexBaseCommand.message());
        if(indexCommand instanceof Mark){
            Mark markCommand = (Mark)indexCommand;
            tasks.get(index-1).markAsDone(markCommand.isMark());
        }
        inputs.add(tasks.get(index-1).toString());
        if(indexCommand instanceof  Delete){
            tasks.remove(index-1);
        }
        Conversation.echo(inputs);
    }

    public abstract String message();
}
