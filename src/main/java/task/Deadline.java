package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    protected LocalDateTime time;

    public Deadline(String description, String timeString) {
        super(description);
        timeString = timeString.trim();
        String[] isTime = timeString.split(" ");
        if (isTime.length > 1) {
            handleDateTime(timeString);
        } else {
            handleDateTime(timeString + " 0000");
        }
    }
    private void handleDateTime(String time) {

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

        this.time = LocalDateTime.parse(time, formatter);
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