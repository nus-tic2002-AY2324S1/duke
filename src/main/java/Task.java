import java.time.LocalDateTime;

/**
 * The Task class is the default base for all task-related classes.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructor of Task take in description and the 'isDone' status set to false
     *
     * @param description The description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone=false;
    }
    public String printDate(LocalDateTime date){
        String zero = "0";
        String builddate = "";
        if(date.getDayOfMonth()<10){
            builddate = builddate + zero;
        }
        builddate = builddate + date.getDayOfMonth() + "/";
        if(date.getMonthValue()<10){
            builddate = builddate + zero;
        }
        builddate = builddate + date.getMonthValue() + "/";
        builddate = builddate + date.getYear() + " ";
        if(date.getHour()<10){
            builddate = builddate + zero;
        }
        builddate = builddate + date.getHour() + ":";

        if(date.getMinute()<10){
            builddate = builddate + zero;
        }
        builddate = builddate + date.getMinute();
        return builddate;
    }
    /**
     * Retrieves the Duke description associated with the task.
     *
     * @return A string representing the Duke description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Marks the task as done or not done based on the provided boolean value.
     *
     * @param markChange A boolean value indicating whether the task is done (true) or not done (false).
     */
    public void mark(boolean markChange){

        this.isDone = markChange;
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
     * Checks if the task is marked as done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean getIsDone(){return isDone;}
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

