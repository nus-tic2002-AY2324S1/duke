package task;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime time;
    protected LocalDateTime time1;


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
     * This class represents a event task that user input only have /from
     *
     * @param description
     * @param timeString
     */
    public Event(String description, String timeString) {
        super(description);
        formatTimeString(timeString);
    }

    /**
     * This method will format the time string to the correct format.
     *
     * @param timeString the time string from the user.
     *                   if user never put the time, it will set the time to 0000.
     */
    private void formatTimeString(String timeString) {
        timeString = timeString.trim();
        LocalDateTime dateTime = DateTimeUtils.parseNextDay(timeString);
        if (dateTime !=null){
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
     * This class represents a event task that user input have /from and /to
     *
     * @param description
     * @param timeString
     * @param timeStringTo
     */
    public Event(String description, String timeString, String timeStringTo) {
        super(description);
        formatTimeString(timeString);
        timeStringTo = timeStringTo.trim();
        LocalDateTime dateTime = DateTimeUtils.parseNextDay(timeStringTo);
        if (dateTime !=null){
            this.time1 = dateTime;
            return;
        }
        String[] isTime1 = timeStringTo.split(" ");
        if (isTime1.length > 1) {
            this.time1 = handleDateTime(timeStringTo);
        } else {
            this.time1 = handleDateTime(timeStringTo + " 0000");
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
        if (this.time1 == null) {
            return "[E]" + super.toString()
                    + " (from: " + timeString + ")";
        }
        boolean isSameDay = this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                .equals(this.time1.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        String timeString1;
        if (isSameDay) {
            timeString1 = this.time1.format(DateTimeFormatter.ofPattern("hh:mma"));
        } else {
            timeString1 = this.time1.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        }
        return "[E]" + super.toString()
                + " (from: " + timeString + " ➞ to: " + timeString1 + ")";
    }

    /**
     * This method is to save the data to the local file
     */
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