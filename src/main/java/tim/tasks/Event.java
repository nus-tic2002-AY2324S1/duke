package tim.tasks;
import tim.body.DateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String description, String from, String to) throws DateException {
        super(description);
        this.setType('E');
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        if(this.from.isAfter(this.to)){
            throw new DateException("improper date");
        }
    }

    @Override
    public String getDescription(){
        return (super.getDescription() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +  ")");
    }


}
