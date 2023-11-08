package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task.
 */
public class Task {
    public static final String DATE_TIME_OUTPUT_FORMAT = "d MMM yyyy, E hh:mma";
    public static final String DATE_TIME_OUTPUT_FORMAT_THIS_YEAR = "d MMM, E hh:mma";
    public static final String DATE_TIME_STORAGE_DEFAULT_FORMAT = "yyyy/M/d HHmm";

    protected String description;
    protected boolean isDone;

    /**
     * Returns a task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? " ✓ " : "҉҉҉");
    }

    /**
     * Sends new status to the task.
     * This method is to set the task is done or not.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the task is done or not.
     * This method is to get the task done or not.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns the date and time of the task.
     * It will cover four different formats of the date and time.
     *
     * @param time the time string from the user.
     * @return The LocalDateTime object to the format requirement.
     */
    protected LocalDateTime handleDateTime(String time) {
        // check the format of the time user input
        DateTimeFormatter formatter;
        if (time.contains("/")) {
            formatter = getDateTimeSlashFormatter(time);
        } else {
            formatter = getDateTimeDashFormatter(time);
        }
        return LocalDateTime.parse(time, formatter);
    }

    private static DateTimeFormatter getDateTimeDashFormatter(String time) {
        DateTimeFormatter formatter;
        String[] checkFormat = time.split("-");
        if (checkFormat[0].length() > 2) {
            formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        } else {
            formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        }
        return formatter;
    }

    private static DateTimeFormatter getDateTimeSlashFormatter(String time) {
        DateTimeFormatter formatter;
        String[] checkFormat = time.split("/");
        if (checkFormat[0].length() <= 2) {
            formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy/M/d HHmm");
        }
        return formatter;
    }

    /**
     * Returns task in string format.
     *
     * @return the type, description, status of the task
     */
    @Override
    public String toString() {
        // Capitalize the first letter of the description
        this.description = this.description.substring(0, 1).toUpperCase() + this.description.substring(1);
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a task in string format.
     * This method is to save the data to the local file.
     */
    public String toStorageString() {
        String status = isDone ? "1" : "0";
        String description = this.description;
        return status + " || " + description;
    }

    /**
     * Returns a clone of the task.
     * This method is to clone the task.
     * It will use it for the undo command.
     *
     * @return the clone task.
     */
    public Task clone() {
        Task cloneTask = new Task(this.description);
        cloneTask.setDone(this.isDone);
        return cloneTask;
    }
}