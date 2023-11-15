import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task
 */

public class Task {

    /** Task variables */
    protected String taskDescription;
    protected String type;
    protected boolean isDone;

    /**
     * Constructs a Task
     * @param Description Description of the Task
     * @param isDone Whether the Task is done or not
     */
    public Task (String Description, boolean isDone){
        this.taskDescription = Description;
        this.isDone = isDone;
        this.type = "Task";
    }

    /**
     * Returns the status of the task
     * @return Boolean of the status
     */
    public String getIsDone(){
        if (this.isDone == false) {
            return "Not Done";
        } else {
            return "Done";
        }
    }

    /**
     * Represents all task variables into a string to be saved into a file
     * @return A formatted string representing the task object for storage.
     */
    public String toFileString() {
        String returnString = this.type + " | " + this.isDone + " | " + this.taskDescription;
        return returnString;
    }

    /**
     * Parses an input to determine if it is LocalDate format, LocalDateTime format, or LocalTime format
     * @param input
     * @return Temporal object of the LocalDate or LocalDateTime
     */

    private static Temporal checkTimeOrNot(String input) {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException ignored) {
            // Ignore because this means it's not LocalDateTime but LocalDate instead
        }

        try {
            return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException ignored) {
            // Ignore because this means it's not LocalTime
        }

        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }

    /**
     * Creates a task object based on a string of details from reading a file
     * @param taskDetails
     * @return The Task object
     */
    public static Task fromString(String taskDetails){
        String[] parts = taskDetails.split(" \\| ");

        if (parts.length >= 3) {
            String taskType = parts[0]; // "Task", "Deadline", or "Event"
            boolean isDone = Boolean.parseBoolean(parts[1]);
            String description = parts[2];

            if ("Task".equals(taskType)) {
                return new Task(description, isDone);

            } else if ("Deadline".equals(taskType)) {
                Temporal doneBefore = checkTimeOrNot(parts[3]);
                if (doneBefore != null) {
                    return new Deadline(description, isDone, doneBefore);
                } else {
                    System.out.println("Invalid date format for 'Deadline' command.");
                }

            } else if ("Event".equals(taskType)) {

                Temporal from = checkTimeOrNot(parts[3]);
                Temporal to = checkTimeOrNot(parts[4]);
                if (from != null && to != null) {
                    return new Event(description, isDone, from, to);
                } else {
                    System.out.println("Invalid date format for 'Event' command.");
                }
            }

        } else {
            System.out.println("[-] An entry wasn't recognized - data could have been corrupted. Continuing to the next entry...");
            return null;
        }

        return null;
    }

    /**
     * Marks the task as done
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Unmarks the task
     */
    public void unmarkDone(){
        this.isDone = false;
    }

    /**
     * Represents all task variables into a string to be printed
     * @return A printable string of the task object
     */
    public String toString() {
        return "[Todo] [" + getIsDone() + "] " + this.taskDescription;
    }

    /**
     * Returns task description
     * @return Returns task decription
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

}
