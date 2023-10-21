package duke.task;

import java.util.ArrayList;

import duke.ui.Ui;

public abstract class Task {
    protected String dukeDescription;
    protected  boolean isDone;
    protected char abbreviation;

    public Task(String description){
        this.dukeDescription = description;
        isDone = false;
    }


    public Task(){
        isDone = false;
    }

    protected static String responseNumberOfTasks() {
        int numberOfTasks = TaskList.size() + 1;
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
    public char getAbbreviation() {
        return abbreviation;
    }
    public String getDukeDescription() {
        return dukeDescription;
    }
    public boolean isDone() {
        return isDone;
    }
    public int getDone(){
        if(isDone){
            return 1;
        }
        return 0;
    }
    public void setDone(boolean done){
        isDone = done;
    }
    public void setDukeDescription(String description){
        dukeDescription = description;
    }
}
