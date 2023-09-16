import java.util.ArrayList;

public class List extends Duke implements ICommand{

    public List(){
        response();
    }

    public void response(){
        if(Task.getNumberOfTasks() == 0){
            String str = "Your list is empty. Let's start adding some items! :)";
            Conversation.echo(str);
        }else{
            ArrayList<Task> tasks = getTasks();
            ArrayList<String> inputs = new ArrayList<>();
            inputs.add("Here are the tasks in your list:");
            for (int i = 0; i <= tasks.size()-1; i++) {
                inputs.add(String.format("%d.%s",i+1, tasks.get(i).toString()));
            }
            Conversation.echo(inputs);
        }
    }
}
