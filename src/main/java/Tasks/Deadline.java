package Tasks;

import java.time.temporal.Temporal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */


public class Deadline extends Task {

    /** Specialized deadline variable */

    protected Temporal doneBefore;

    /**
     * Constructs a Deadline
     *
     * @param description
     * @param isDone
     * @param doneBefore
     */
    public Deadline(String description, boolean isDone, Temporal doneBefore){
        super(description, isDone);
        this.doneBefore = doneBefore;
        this.isDone = isDone;
        this.type = "Deadline";
    }

    /**
     * Represents all Deadline variables as a string to be saved into a file
     *
     * @return A formatted string representing the Deadline object for storage.
     */
    public String toFileString() {
        String returnString = this.type + " | " + this.isDone + " | " + this.taskDescription + " | " + this.doneBefore;
        return returnString;

    }

    /**
     * Represents all Deadline variables into a string to be printed
     *
     * @return  A printable string of the Deadline object.
     */
    public String toString() {
        String isDoneStr = getIsDone();
        String taskDescriptionStr = this.taskDescription;
        String doneBeforeStr;

        if (doneBefore instanceof LocalDateTime) {
            doneBeforeStr = ((LocalDateTime) doneBefore).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (doneBefore instanceof LocalDate) {
            doneBeforeStr = ((LocalDate) doneBefore).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (doneBefore instanceof LocalTime) {
            doneBeforeStr = ((LocalTime) doneBefore).format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            doneBeforeStr = "Unknown";
        }

        return "[Deadline] [" + isDoneStr + "] " + taskDescriptionStr + " (By: " + doneBeforeStr + ")";
    }

    /**
     * Returns Deadline date
     *
     * @return Returns date
     */
    public Temporal getDoneBefore() {
        return this.doneBefore;
    }
}
