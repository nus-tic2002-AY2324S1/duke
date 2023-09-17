import java.util.ArrayList;

public abstract class Task implements ICommand{
    protected String description;
    protected  boolean isDone;
    protected char abbreviation;
    private static int numberOfTasks = 0;

    public Task(String description){
        this.description = description;
        isDone = false;

        numberOfTasks++;
    }

    public Task(){}

    protected static void responseNumberOfTasks() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(String.format("Now you have %d %s in the list.",numberOfTasks,numberOfTasks>1?"tasks":"task"));
        Conversation.echoWithBottomLine(inputs);
    }

    protected static void responseGotIt() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("Got it. I've added this task:");
        Conversation.echoWithTopLine(inputs);
    }

    public String getStatusIcon(){
        return (isDone? "X":" ");
    }

    public void markAsDone(boolean done){
        this.isDone = done;
    }

    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

    @Override
    public abstract String toString();

}
