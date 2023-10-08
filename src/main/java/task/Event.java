package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;
    protected LocalDateTime time1;

    public Event(String description, String timeString) {
        super(description);
        formatTimeString(timeString);
    }

    private void formatTimeString(String timeString) {
        timeString = timeString.trim();
        String[] isTime = timeString.split(" ");
        if (isTime.length > 1) {
            this.time = handleDateTime(timeString);
        } else {
            this.time = handleDateTime(timeString + " 0000");
        }
    }

    public Event(String description, String timeString, String timeString1) {
        super(description);
        formatTimeString(timeString);
        timeString1 = timeString1.trim();
        String[] isTime1 = timeString1.split(" ");
        if (isTime1.length > 1) {
            this.time1 = handleDateTime(timeString1);
        } else {
            this.time1 = handleDateTime(timeString1 + " 0000");
        }
    }

    @Override
    public String toString() {
        String tmp = String.valueOf(this.time.getDayOfWeek());
        String dateOfWeek = tmp.substring(0, 1) + tmp.substring(1).toLowerCase();
        String timeString = this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy, ha")) + " - " + dateOfWeek;
        if (this.time1 == null) {
            return "[E]" + super.toString()
                    + " (from: " + timeString.trim() + ")";
        }
        String tmp1 = String.valueOf(this.time1.getDayOfWeek());
        String dateOfWeek1 = tmp1.substring(0, 1) + tmp1.substring(1).toLowerCase();
        String timeString1 = this.time1.format(DateTimeFormatter.ofPattern("d MMM yyyy, ha")) + " - " + dateOfWeek1;
        return "[E]" + super.toString()
                + " (from: " + timeString.trim() + " ➞ to: " + timeString1 + ")";
    }

    @Override
    public String toStorageString() {
        if (this.time1 == null) {
            return "[E]" + super.toString()
                    + " (from: " + time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")) + ")";
        }
        return "[E]" + super.toString()
                + " (from: " + time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")) +
                " to: " + time1.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")) + ")";

    }
}