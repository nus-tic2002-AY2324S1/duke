package tim.tasks;
import tim.exceptions.DateException;
import tim.ui.Display;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents as an Event object.
 * This class is a subclass of Task.
 * It has a description, a fromDate and a toDate.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Creates an Event object.
     *
     * @param description the description of the task.
     * @param fromDate the date of the event.
     * @param toDate the date of the event.
     * @throws DateException if the fromDate is after toDate.
     */
    public Event(String description, String fromDate, String toDate) throws DateException {
        super(description);
        this.setType('E');
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
        if(this.fromDate.isAfter(this.toDate)){
            throw new DateException("improper date");
        }
    }

    /**
     * Postpones the event's toDate to the specified date.
     * New toDate must be after the original ToDate.
     *
     * @param extendedToDate the new date of the event.
     * @throws DateException if the new date is before the original event period.
     */
    public void postponeToDate(String extendedToDate) throws DateException {
        LocalDate parsedExtendedToDate = LocalDate.parse(extendedToDate);
        if(parsedExtendedToDate.isBefore(this.fromDate)
                || parsedExtendedToDate.isBefore(this.toDate) ){
            System.out.println("oh no!  New date is before the original event period!");
            Display.printDash();
            throw new DateException("improper date");
        }
        this.toDate = LocalDate.parse(extendedToDate);
    }

    /**
     * Snoozes the event by one week.
     *
     * @throws DateException if the new date is before the original event period.
     */
    public void snoozeToDateByOneWeek() throws DateException {
        assert  this.toDate != null : "assert parsedExtendedByDate is not null";
        this.toDate = this.toDate.plusDays(ONE_WEEK);
    }


    public LocalDate getFromDate () {
        return this.fromDate;

    }

    public LocalDate getToDate () {
        return this.toDate;

    }

    /**
     * @inheritDoc
     *
     * @return description of the task with the fromDate and toDate.
     */
    @Override
    public String getDescription(){
        return (super.getDescription() + "(from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +  ")");
    }


}
