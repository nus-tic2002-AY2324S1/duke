package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{

    // accept date format: yyyy-mm-dd (2019-10-15)
    // print in format: MMM dd yyyy (Oct 15 2019)
    protected LocalDate start; 
    protected LocalDate end;
 

    public Event(String description, LocalDate start, LocalDate end){
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDate getDueDate(){
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String writeFile() {
        return "E|" + super.writeFile() + "|" + start + "|" + end;
    }
}
