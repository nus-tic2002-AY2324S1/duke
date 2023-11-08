package task;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline task.
 * It extends from the Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * Returns the LocalDateTime object.
     * This method is to handle the date and time.
     * It will use it for the sort function.
     *
     * @return The LocalDateTime object.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Adds a deadline tasks.
     *
     * @param description the description of the task.
     * @param timeString  the time of the task.
     */
    public Deadline(String description, String timeString) {
        super(description);
        assert timeString != null;
        timeString = timeString.trim();
        LocalDateTime dateTime = DateTimeUtils.parseNextDay(timeString);
        if (dateTime != null) {
            this.time = dateTime;
            return;
        }
        String[] isTime = timeString.split(" ");
        if (isTime.length > 1) {
            this.time = handleDateTime(timeString);
        } else {
            this.time = handleDateTime(timeString + " 0000");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        LocalDateTime now = LocalDateTime.now();
        String timeString;
        if (now.getYear() == this.time.getYear()) {
            timeString = this.time.format(DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMAT_THIS_YEAR));
        } else {
            timeString = this.time.format(DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMAT));
        }
        return "[D]" + super.toString() + " || before: " + timeString.trim();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStorageString() {
        String type = "D";
        String status = isDone ? "1" : "0";
        String description = this.description;
        String time = this.time.format(DateTimeFormatter.ofPattern(DATE_TIME_STORAGE_DEFAULT_FORMAT));
        return type + " || " + status + " || " + description + " || " + time;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Task clone() {
        Deadline clone = new Deadline(this.description,
                                      this.time.format(DateTimeFormatter.ofPattern(DATE_TIME_STORAGE_DEFAULT_FORMAT)));
        clone.setDone(this.isDone);
        return clone;
    }
}