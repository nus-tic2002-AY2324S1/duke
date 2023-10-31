package tim.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is a subclass of Task.
 * It is used to create a Deadline object.
 * It has a description and a byDate.
 *
 * @param description the description of the task
 * @param byDate the date of the deadline
 */

public class Deadline extends Task {
    private LocalDate byDate;
    public Deadline(String description, String byDate){
        super(description);
        this.byDate = LocalDate.parse(byDate);
        this.setType('D');
    }
    @Override
    public String getDescription(){
        return (super.getDescription() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
