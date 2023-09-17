import java.util.ArrayList;

public abstract class Task implements ICommand{
    protected String dukeDescription;
    protected  boolean isDone;
    protected char abbreviation;
    private static int numberOfTasks = 0;

    public Task(String description){
        this.dukeDescription = description;
        isDone = false;
    }

    protected static void increaseNumberOfTaskByOne() {
        numberOfTasks++;
    }

    public Task(){
        isDone = false;
    }

    protected static String responseNumberOfTasks() {
        return String.format("Now you have %d %s in the list.",numberOfTasks,numberOfTasks>1?"tasks":"task");
    }

    protected static String responseGotIt() {
        return "Got it. I've added this task:";
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

    @Override
    public void response(){
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(responseGotIt());
        inputs.add(" " + this.toString());
        inputs.add(responseNumberOfTasks());
        Conversation.echo(inputs);
    }

}
