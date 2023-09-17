import java.util.ArrayList;

public class Mark extends Duke implements ICommand{
    protected boolean isMark;
    private int index;

    public Mark(){
        this.isMark = true;
    }
    public Mark(String taskDescription){
        this.isMark = true;
        process(taskDescription);
    }

    protected void process(String taskDescription) {
        try {
            putIndex(taskDescription);
            response();
        } catch (NumberFormatException e) {
            String str = String.format("The \"index number\" of the \"%s\" must be an integer :-(", isMark ?"mark":"unmark");
            Conversation.echo(str);
        } catch (IndexOutOfBoundsException e) {
            String str = String.format("The \"index number\" of the \"%s\" is out of range :-(", isMark ?"mark":"unmark");
            Conversation.echo(str);
        }
    }

    private void putIndex(String input){
        index = Integer.parseInt(input);
    }
    
    public void response(){
        ArrayList<Task> tasks = getTasks();
        ArrayList<String> inputs = new ArrayList<>();
        if(isMark){
            inputs.add("Nice! I've marked this task as done:");
        }else{
            inputs.add("OK, I've marked this task as not done yet:");
        }
        tasks.get(index-1).markAsDone(isMark);
        inputs.add(tasks.get(index-1).toString());
        Conversation.echo(inputs);

    }
}
