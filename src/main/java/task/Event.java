package task;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task that have a description and a time.
 * It is a subclass of Task.
 */
public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    /**
     * Returns fromTime.
     * This method is to handle the date and time.
     * It will use for the sort function.
     *
     * @return The LocalDateTime object
     */
    public LocalDateTime getFromTime() {
        return fromTime;
    }

    /**
     * Returns an event task that user input have /from and /to
     * This class represents an event task that user input only have /from
     *
     * @param description the description of the event task.
     * @param timeString  the time string from the user.
     */
    public Event(String description, String timeString) {
        super(description);
        formatTimeString(timeString);
    }

    /**
     * This method will format the time string to the correct format.
     *
     * @param timeString the time string from the user.
     */
    private void formatTimeString(String timeString) {
        timeString = timeString.trim();
        LocalDateTime dateTime = DateTimeUtils.parseNextDay(timeString);
        if (dateTime != null) {
            this.fromTime = dateTime;
            return;
        }
        String[] isTime = timeString.split(" ");
        if (isTime.length > 1) {
            this.fromTime = handleDateTime(timeString);
        } else {
            this.fromTime = handleDateTime(timeString + " 0000");
        }
    }

    /**
     * Returns an event task that user input have /from and /to
     * This class represents an event task that user input have /from and /to
     * It will format the time string to the correct format.
     * It will use for the sort date function.
     *
     * @param description  the description of the event task.
     * @param timeString   the time string from the user.
     * @param timeStringTo the time string from the user.
     */
    public Event(String description, String timeString, String timeStringTo) {
        super(description);
        formatTimeString(timeString);
        timeStringTo = timeStringTo.trim();
        LocalDateTime dateTime = DateTimeUtils.parseNextDay(timeStringTo);
        if (dateTime != null) {
            this.toTime = dateTime;
            return;
        }
        String[] isTime1 = timeStringTo.split(" ");
        if (isTime1.length > 1) {
            this.toTime = handleDateTime(timeStringTo);
        } else {
            this.toTime = handleDateTime(timeStringTo + " 0000");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String timeString = this.fromTime.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        if (this.toTime == null) {
            return "[E]" + super.toString() + " (from: " + timeString + ")";
        }
        boolean isSameDay = this.fromTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                .equals(this.toTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        String timeString1;
        if (isSameDay) {
            timeString1 = this.toTime.format(DateTimeFormatter.ofPattern("hh:mma"));
        } else {
            timeString1 = this.toTime.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        }
        return "[E]" + super.toString() + " (from: " + timeString + " ➞ to: " + timeString1 + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStorageString() {
        String type = "E";
        String status = isDone ? "1" : "0";
        String description = this.description;
        String time = this.fromTime.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm"));
        if (this.toTime == null) {
            return type + " || " + status + " || " + description + " || " + time;
        }
        String timeOfTo = this.toTime.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm"));
        return type + " || " + status + " || " + description + " || " + time + " || " + timeOfTo;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Event clone() {
        Event event = new Event(this.description, this.fromTime.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")));
        event.setDone(this.isDone);
        event.toTime = this.toTime;
        return event;
    }
}