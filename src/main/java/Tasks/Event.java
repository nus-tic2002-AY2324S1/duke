package Tasks;

import java.time.temporal.Temporal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a time limit.
 */

public class Event extends Task {

    /** Specialized event variables */

    protected Temporal doneFrom;
    protected Temporal doneTo;

    /**
     * Constructs Event
     *
     * @param description Task's description
     * @param isDone If Task is done or not
     * @param from Start date of Event
     * @param to End date of Event
     */
    public Event(String description, boolean isDone, Temporal from, Temporal to){
        super(description, isDone);
        this.doneFrom = from;
        this.doneTo = to;
        this.isDone = isDone;
        this.type = "Event";
    }

    /**
     * Represents all event variables as a string to be saved into a file
     *
     * @return returnString
     */
    public String toFileString() {
        String returnString = this.type + " | " + this.isDone + " | " + this.taskDescription + " | " + this.doneFrom + " | " + this.doneTo;
        return returnString;

    }

    /**
     * Represents all event variables as a string to be printed
     *
     * @return String to be parsed
     */
    public String toString() {
        String isDoneStr = getIsDone();
        String taskDescriptionStr = this.taskDescription;
        String doneFromStr;
        String doneToStr;

        if (doneFrom instanceof LocalDateTime) {
            doneFromStr = ((LocalDateTime) doneFrom).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (doneFrom instanceof LocalDate) {
            doneFromStr = ((LocalDate) doneFrom).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (doneFrom instanceof LocalTime) {
            doneFromStr = ((LocalTime) doneFrom).format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            doneFromStr = "Unknown";
        }

        if (doneTo instanceof LocalDateTime) {
            doneToStr = ((LocalDateTime) doneTo).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (doneTo instanceof LocalDate) {
            doneToStr = ((LocalDate) doneTo).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (doneTo instanceof LocalTime) {
            doneToStr = ((LocalTime) doneTo).format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            doneToStr = "Unknown";
        }

        return "[Event] [" + isDoneStr + "] " + taskDescriptionStr + " (From: " + doneFromStr + " To: " + doneToStr + ")";
    }

    /**
     * Returns Event date
     *
     * @return Returns date From
     */
    public Temporal getDoneFrom() {
        return this.doneFrom;
    }

    /**
     * Returns Event date
     *
     * @return Returns date To
     */
    public Temporal getDoneTo() {
        return this.doneTo;
    }
}
