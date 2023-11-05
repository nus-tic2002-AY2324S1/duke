package tim.tasks;
import tim.body.DateException;

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

    public void postponeByDate(String extendedToDate) throws DateException {
        LocalDate parsedExtendedToDate = LocalDate.parse(extendedToDate);
        if(parsedExtendedToDate.isBefore(this.byDate) ){
            throw new DateException("improper date");
        }
        this.byDate = LocalDate.parse(extendedToDate);
    }

    public void snoozeByDateByOneWeek() throws DateException {
        this.byDate = byDate.plusDays(7);
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
