package TaskClasses;

import ExceptionClasses.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws IncompleteDataException {
        //parser for user input
        super(description);
        if (by == null || by.isEmpty()) {
            throw new IncompleteDataException("Deadline 'by' information is missing");
        }
        parseDeadlineDateTime(by);
    }

    public Deadline(String description, String by, boolean isDone) throws IncompleteDataException {
        //parser for saved file
        super(description, isDone);
        if (by == null || by.isEmpty()) {
            throw new IncompleteDataException("Deadline 'by' information is missing");
        }
        parseDeadlineDateTime(by);
    }

    private void parseDeadlineDateTime(String by) throws IncompleteDataException {
        //System.out.println(by);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd['T'HH:mm][ HH:mm]");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new IncompleteDataException("Invalid date format for Deadline 'by' information");
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toFileString() {
        String isDoneSymbol = getIsDone() ? "1" : "0";
        return "D | " + isDoneSymbol + " | " + description + " | " + by;
    }
}
