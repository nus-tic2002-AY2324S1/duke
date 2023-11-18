package duke.task;

import java.util.ArrayList;

import duke.common.Message;
import duke.ui.Ui;

/**
 * The Task class serves as the base for all task-related classes.
 */
public abstract class Task {
    public static final String DATE_TIME_FORMAT_DISPLAY = "MMM dd yyyy HH:mm";
    public static final String DATE_FORMAT_DISPLAY = "MMM dd yyyy";
    public static final String DATE_TIME_FORMAT_TOFILE = "dd/MM/yyyy HHmm";
    public static final String DATE_FORMAT_TOFILE = "dd/MM/yyyy";
    protected String dukeDescription;
    protected boolean isDone;
    protected char abbreviation;

    /**
     * Constructs a new Task object with the specified description.
     *
     * @param description The description for the Task.
     */
    public Task(String description) {
        this.dukeDescription = description;
        isDone = false;
    }

    /**
     * Constructs a new task with default values. The task is initially set as not done.
     */
    public Task() {
        isDone = false;
    }

    /**
     * Generates a response based on the number of tasks in the TaskList.
     *
     * @return A response indicating the number of tasks in the TaskList.
     */
    protected static String responseNumberOfTasks() {
        int numberOfTasks = TaskList.size();
        return String.format("Now you have %d %s in the list.", numberOfTasks, numberOfTasks > 1 ? "tasks" : "task");
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representation of the task's status, "X" if done, " " (space) if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done based on the provided boolean value.
     *
     * @param done A boolean value indicating whether the task is done (true) or not done (false).
     */
    public void markAsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a string representation of the object. The returned string should provide a clear and concise
     * description of the object's state. Subclasses should override this method to provide specific implementations.
     *
     * @return A string representation of the object.
     */
    @Override
    public abstract String toString();

    /**
     * Displays a processed task response to the user, including a custom message
     *
     * @param message A custom message to be displayed as part of the response.
     */
    public void displayProcessedTaskResponse(String message) {
        Ui ui = new Ui();
        ArrayList<String> messages = new ArrayList<>();
        messages.add(message);
        messages.add(" " + this.toString());
        messages.add(responseNumberOfTasks());
        ui.showResponseToUser(messages);
    }

    /**
     * Retrieves the abbreviation associated with the task.
     *
     * @return A character representing the abbreviation of the task.
     */
    public char getAbbreviation() {
        return abbreviation;
    }

    /**
     * Retrieves the Duke description associated with the task.
     *
     * @return A string representing the Duke description of the task.
     */
    public String getDukeDescription() {
        return dukeDescription;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts the task's "done" status to a binary representation.
     *
     * @return 1 if the task is done, 0 if the task is not done.
     */
    public int convertTaskStatusToBinary() {
        if (isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * Updates the description of the tasks.
     *
     * @param description The new description to set for the tasks.
     */
    public void updateDescription(String description) {
        dukeDescription = description;
    }
}
