package Duke.TaskClasses;

import Duke.ExceptionClasses.IncompleteDataException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;


    public Event(String description, String from, String to) throws IncompleteDataException {
        super(description);
        if (from == null || from.isEmpty()) {
            throw new IncompleteDataException("Missing 'from' information in Event task");
        }
        if (to == null || to.isEmpty()) {
            throw new IncompleteDataException("Missing 'to' information in Event task");
        }
        try {
            // Attempt to parse "from" and "to" strings into LocalDateTime
            this.from = parseEventDateTime(from);
            this.to = parseEventDateTime(to);
        } catch (DateTimeParseException e) {
            // Handle the case where the date/time strings are not in the expected format
            throw new IncompleteDataException("Invalid date/time format in Event task: " + e.getMessage());
        }
    }

    public Event(String description, String from, String to, boolean isDone) throws IncompleteDataException {
        super(description, isDone);
        if (from == null || from.isEmpty()) {
            throw new IncompleteDataException("Missing 'from' information in Event task");
        }
        if (to == null || to.isEmpty()) {
            throw new IncompleteDataException("Missing 'to' information in Event task");
        }
        try {
            // Attempt to parse "from" and "to" strings into LocalDateTime
            this.from = parseEventDateTime(from);
            this.to = parseEventDateTime(to);
        } catch (DateTimeParseException e) {
            // Handle the case where the date/time strings are not in the expected format
            throw new IncompleteDataException("Invalid date/time format in Event task: " + e.getMessage());
        }

        System.out.println("test: " + this.from.toString());
    }

    public Event(String description, LocalDateTime from, LocalDateTime to){
        super(description);
        this.from = from;
        this.to = to;
    }


    private LocalDateTime parseEventDateTime(String dateTimeString) throws IncompleteDataException{
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy/MM/dd['T'HH:mm][ HH:mm]"));
        } catch (DateTimeParseException e1) {
            try {
                // Add an alternative date-time format here
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            } catch (DateTimeParseException e2) {
                // Handle the case where both formats fail
                throw new IncompleteDataException("Invalid date/time format in Event task: " + e1.getMessage() + ", " + e2.getMessage());
            }
        }
    }


    public LocalDateTime getFromDateTime() {
        return from;
    }

    public LocalDateTime getToDateTime() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        String isDoneSymbol = getIsDone() ? "1" : "0";
        return "E | " + isDoneSymbol + " | " + description + " | " + from + " | " + to;
    }
}
