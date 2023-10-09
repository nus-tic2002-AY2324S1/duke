package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? " ✓ " : "҉҉҉");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    protected LocalDateTime handleDateTime(String time) {

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

    @Override
    public String toString() {
        this.description = this.description.substring(0, 1).toUpperCase()
                + this.description.substring(1);
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toStorageString() {
        return this.toString();
    }

}