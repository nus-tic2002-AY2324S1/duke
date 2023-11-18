package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStartDate() {
        return this.start;
    }

    public LocalDate getEndDate() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String writeToFile() {
        return "E|" + super.writeToFile() + "|" + start + "|" + end;
    }
}
