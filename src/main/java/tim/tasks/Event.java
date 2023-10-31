package tim.tasks;
import tim.body.DateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is a subclass of Task.
 * It is used to create an Event object.
 * It has a description, a fromDate and a toDate.
 *
 * @param description the description of the task
 * @param fromDate the starting date of the event
 * @param toDate the ending date of the event
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;
    public Event(String description, String fromDate, String toDate) throws DateException {
        super(description);
        this.setType('E');
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
        if(this.fromDate.isAfter(this.toDate)){
            throw new DateException("improper date");
        }
    }

    @Override
    public String getDescription(){
        return (super.getDescription() + "(from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +  ")");
    }


}
