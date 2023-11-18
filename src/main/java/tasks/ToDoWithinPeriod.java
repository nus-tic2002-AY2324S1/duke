package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDoWithinPeriod extends Task{
    private LocalDate from;
    private LocalDate to;

    public ToDoWithinPeriod(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFromDate() {
        return this.from;
    }

    public LocalDate getToDate() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " between "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " and "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String writeToFile() {
        return "T|" + super.writeToFile() + "|" + from + "|" + to;
    }
    
}
