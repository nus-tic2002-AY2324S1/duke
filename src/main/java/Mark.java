import java.util.ArrayList;

public class Mark extends Duke implements ICommand{
    private boolean isMark;
    private int index;
    public Mark(String taskDescription, boolean isMark){
        this.isMark = isMark;
        try {
            putIndex(taskDescription);
            response();
        } catch (NumberFormatException e) {
            String str = String.format("The index number of the \"%s\" must be an integer :-(",isMark?"mark":"unmark");
            Conversation.echo(str);
        } catch (IndexOutOfBoundsException e) {
            String str = String.format("The index number of the \"%s\" is out of range :-(",isMark?"mark":"unmark");
            Conversation.echo(str);

        }
    }


    private void putIndex(String input){
        index = Integer.parseInt(input);
    }
    @Override
    public void response(){
        ArrayList<Task> tasks = getTasks();
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("Nice! I've marked this task as done:");
        tasks.get(index-1).markAsDone(isMark);
        inputs.add(tasks.get(index-1).toString());
        Conversation.echo(inputs);

    }
}
