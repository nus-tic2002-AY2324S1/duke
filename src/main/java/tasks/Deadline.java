package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    //protected String start;
    // accept date format: yyyy-mm-dd (2019-10-15)
    // print in format: MMM dd yyyy (Oct 15 2019)
    //protected String end;

    //protected String by;
    protected LocalDate by; 

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getDueDate(){
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String writeFile() {
        return "D|" + super.writeFile() + "|" + by;
    }
}
