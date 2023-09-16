public class Bye implements ICommand{

    public Bye(){
        response();
    }
    public void response(){
        String str = "Bye. Hope to see you again soon!";
        Conversation.echo(str);
    }
}
