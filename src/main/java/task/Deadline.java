package task;

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

    public Deadline(String description, String timeString) {
        super(description);
        timeString = timeString.trim();
        String[] isTime = timeString.split(" ");
        if (isTime.length > 1) {
            this.time = handleDateTime(timeString);
        } else {
            this.time = handleDateTime(timeString + " 0000");
        }
    }
    @Override
    public String toString() {
        String tmp = String.valueOf(this.time.getDayOfWeek());
        String dateOfWeek = tmp.substring(0, 1) + tmp.substring(1).toLowerCase();
        String timeString = this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy, ha")) + " - " + dateOfWeek;
        return "[D]" + super.toString()
                + " (by: " + timeString.trim() + ")";
    }

    @Override
    public String toStorageString() {
        return "[D]" + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")) + ")";
    }
}