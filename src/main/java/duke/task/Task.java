package duke.task;

import java.util.ArrayList;

import duke.ui.Ui;

public abstract class Task {
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

    public static void decreaseNumberOfTaskByOne(){
        numberOfTasks--;
    }

    public Task(){
        isDone = false;
    }

    protected static String responseNumberOfTasks() {
        return String.format("Now you have %d %s in the list.",numberOfTasks,numberOfTasks>1?"tasks":"task");
    }

    final static  String RESPONSE_GOT_IT() {
        return "Got it. I've added this task:";
    }

    public String getStatusIcon(){
        return (isDone? "X":" ");
    }

    public void markAsDone(boolean done){
        this.isDone = done;
    }


    @Override
    public abstract String toString();

    public void response(){
        Ui ui = new Ui();
        ArrayList<String> messages = new ArrayList<>();
        messages.add(RESPONSE_GOT_IT());
        messages.add(" " + this.toString());
        messages.add(responseNumberOfTasks());
        ui.showResponseToUser(messages);
    }

}
