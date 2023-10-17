package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public static final String TIME_OUTPUT_FORMAT = "d MMM yyyy, E - hh:mma";
    protected String description;
    protected boolean isDone;
    /**
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? " ✓ " : "҉҉҉");
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * This method is to handle the date and time.
     *
     * @return The LocalDateTime object to the format requirement.
     */
    protected LocalDateTime handleDateTime(String time) {
        // check the format of the time user input
        DateTimeFormatter formatter;
        if (time.contains("/")) {
            String[] checkFormat = time.split("/");
            if (checkFormat[0].length() <= 2) {
                formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyy/M/d HHmm");
            }
        } else {
            String[] checkFormat = time.split("-");
            if (checkFormat[0].length() > 2) {
                formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
            } else {
                formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            }
        }
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * @return the type, description, status of the task
     */
    @Override
    public String toString() {
        // Capitalize the first letter of the description
        this.description = this.description.substring(0, 1).toUpperCase()
                + this.description.substring(1);
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * This method is to save the data to the local file
     */
    public String toStorageString() {
        return this.toString();
    }
    protected Task clone() {
        Task cloneTask = new Task(this.description);
        cloneTask.setIsDone(this.isDone);
        return cloneTask;
    }
}