package duke.task;

import java.util.ArrayList;

import duke.common.Message;
import duke.ui.Ui;

public abstract class Task {
    public static final String DATE_TIME_FORMAT_DISPLAY = "MMM dd yyyy HH:mm";
    public static final String DATE_TIME_FORMAT_TOFILE = "dd/MM/yyyy HHmm";
    protected String dukeDescription;
    protected  boolean isDone;
    protected char abbreviation;
    /**
     * Constructor of Task take in description and the 'isDone' status set to false
     * @param description The description for the Task.
     */
    public Task(String description){
        this.dukeDescription = description;
        isDone = false;
    }
    /**
     * Constructor of Task with the 'isDone' status set to false.
     */
    public Task(){
        isDone = false;
    }
    /**
     * Generates a response based on the number of tasks in the TaskList.
     * @return A response indicating the number of tasks in the TaskList.
     */
    protected static String responseNumberOfTasks() {
        int numberOfTasks = TaskList.size() + 1;
        return String.format("Now you have %d %s in the list.",numberOfTasks,numberOfTasks>1?"tasks":"task");
    }
    /**
     * Returns a response indicating that a task has been successfully added.
     * @return A response message confirming the addition of a task.
     */
    final static  String taskAddedSuccessfullyResponse() {
        return Message.MESSAGE_GOT_IT;
    }
    /**
     * Returns the status icon of the task.
     * @return A string representation of the task's status, "X" if done, " " (space) if not done.
     */
    public String getStatusIcon(){
        return (isDone? "X":" ");
    }
    /**
     * Marks the task as done or not done based on the provided boolean value.
     * @param done A boolean value indicating whether the task is done (true) or not done (false).
     */
    public void markAsDone(boolean done){
        this.isDone = done;
    }
    @Override
    public abstract String toString();
    /**
     * Constructs and displays a response message after adding a task.
     */
    public void displayTaskAddedResponse(){
        Ui ui = new Ui();
        ArrayList<String> messages = new ArrayList<>();
        messages.add(taskAddedSuccessfullyResponse());
        messages.add(" " + this.toString());
        messages.add(responseNumberOfTasks());
        ui.showResponseToUser(messages);
    }
    /**
     * Retrieves the abbreviation associated with the task.
     * @return A character representing the abbreviation of the task.
     */
    public char getAbbreviation() {
        return abbreviation;
    }
    /**
     * Retrieves the Duke description associated with the task.
     * @return A string representing the Duke description of the task.
     */
    public String getDukeDescription() {
        return dukeDescription;
    }
    /**
     * Checks if the task is marked as done.
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Converts the task's "done" status to a binary representation.
     * @return 1 if the task is done, 0 if the task is not done.
     */
    public int convertTaskStatusToBinary(){
        if(isDone){
            return 1;
        }
        return 0;
    }
    /**
     * Sets the task's "isDone" status.
     * @param done A boolean value indicating whether the task is done (true) or not done (false).
     */
    public void setDone(boolean done){
        isDone = done;
    }
    /**
     * Sets the Duke description associated with the task.
     * @param description A string representing the Duke description to be set for the task.
     */
    public void setDukeDescription(String description){
        dukeDescription = description;
    }
}
