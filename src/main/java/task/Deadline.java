package task;

import utils.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This class represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * This method is to handle the date and time.
     * It will use for the sort function.
     *
     * @return The LocalDateTime object
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * This class represents a deadline task that user input have /by
     *
     * @param description
     * @param timeString
     */
    public Deadline(String description, String timeString) {
        super(description);
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
     * This method is to handle the date and time.
     *
     * @return The LocalDateTime object to the format requirement.
     */
    @Override
    public String toString() {
        String timeString = this.time.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        return "[D]" + super.toString()
                + " (before: " + timeString.trim() + ")";
    }

    /**
     * This method is to save the data to the local file
     */
    @Override

    public String toStorageString() {
        return "[D]" + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")) + ")";
    }

    //implement the clone function
    public Task clone() {
        Deadline clone = new Deadline(this.description, this.time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")));
        clone.setIsDone(this.isDone);
        return clone;
    }
}