package tim.tasks;
import tim.exceptions.DateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents as a Deadline object.
 * This class is a subclass of Task.
 * It has a description and a byDate.
 */

public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Creates a Deadline object.
     *
     * @param description the description of the task
     * @param byDate the date of the deadline
     */
    public Deadline(String description, String byDate){
        super(description);
        this.byDate = LocalDate.parse(byDate);
        this.setType('D');
    }

    /**
     * Postpones the deadline to the specified date.
     * New date must be after the original deadline.
     *
     * @param extendedByDate the new date of the deadline
     * @throws DateException if the new date is before the original deadline
     */
    public void postponeByDate(String extendedByDate) throws DateException {
        LocalDate parsedExtendedByDate = LocalDate.parse(extendedByDate);
        System.out.println(extendedByDate);
        if(parsedExtendedByDate.isBefore(this.byDate)){
            System.out.println("oh no!  New date is before the original deadline!");
            throw new DateException("improper date");
        }
        assert  parsedExtendedByDate != null : "assert parsedExtendedByDate is not null";
        this.byDate = LocalDate.parse(extendedByDate);
    }

    /**
     * Snoozes the deadline by one week.
     *
     * @throws DateException if the new date is before the original deadline
     */
    public void snoozeByDateByOneWeek() throws DateException {
        assert  this.byDate != null : "assert parsedExtendedByDate is not null";
        this.byDate = this.byDate.plusDays(ONE_WEEK);
    }

    public LocalDate getByDate () {
        return this.byDate;
    }


    /**
     * @inheritDoc
     *
     * @return description of the task with the byDate.
     */
    @Override
    public String getDescription(){
        return (super.getDescription() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
