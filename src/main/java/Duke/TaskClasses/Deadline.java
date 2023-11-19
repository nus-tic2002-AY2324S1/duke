package Duke.TaskClasses;

import Duke.ExceptionClasses.IncompleteDataException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     *
     * This constructor is used when user adds new task
     */
    public Deadline(String description, String by) throws IncompleteDataException {
        //parser for user input
        super(description);
        if (by == null || by.isEmpty()) {
            throw new IncompleteDataException("Deadline 'by' information is missing");
        }
        parseDateTime(by);
    }

    /**
     * Constructs a task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @throws IncompleteDataException If the task data is incomplete.
     * This constructor is used when loading tasks from a saved file.
     */
    public Deadline(String description, String by, boolean isDone) throws IncompleteDataException {
        //parser for saved file
        super(description, isDone);
        if (by == null || by.isEmpty()) {
            throw new IncompleteDataException("Deadline 'by' information is missing");
        }
        parseDateTime(by);
    }

    private void parseDateTime(String by) throws IncompleteDataException {
        //System.out.println(by);
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd['T'HH:mm][ HH:mm]");
            this.by = LocalDateTime.parse(by, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm][ HH:mm]");
                this.by = LocalDateTime.parse(by, formatter2);
            } catch (DateTimeParseException e2) {
                throw new IncompleteDataException("Invalid date format for Deadline 'by' information");
            }
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
