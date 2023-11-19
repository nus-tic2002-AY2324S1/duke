package Duke.TaskClasses;

import Duke.ExceptionClasses.CorruptedFileException;
import Duke.ExceptionClasses.IncompleteDataException;

/**
 * Represents a general task with a description and completion status.
 */

public class Task {

    protected String description;
    private boolean isDone;

    /**
     * Constructs a task with the given description and sets its completion status to false.
     *
     * @param description The description of the task.
     * This constructor is used when user adds new task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the given description and completion status.
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * This constructor is used when loading task list from a saved file
     */
    public Task(String description, boolean isDone) throws IncompleteDataException {
        if (description == null || description.isEmpty()) {
            throw new IncompleteDataException();
        }

        this.description = description;
        this.isDone = isDone;
    }


    public String getDescription() {
        return description;
    } // Gets the description of the task.

    public void markAsDone() {
        isDone = true;
    } //marks task as done

    public void markAsNotDone() {
        isDone = false;
    } //marks task as not done

    //gets the status icon for the task, [X] is done, [ ] is not done, this is used when generating task list for user to see
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }


    // Converts the task to a string representation for file storage
    public String toFileString() {
        String isDoneSymbol = isDone ? "1" : "0";
        return "T | " + isDoneSymbol + " | " + description;
    }


    protected boolean getIsDone() {
        return isDone;
    }


    /**
     * Creates a task object from a string representation stored in a file.
     *
     * @param fileString The string representation of the task stored in a file.
     * @return The task object created from the file string.
     * @throws CorruptedFileException  If the file data is corrupted.
     * @throws IncompleteDataException If the task data is incomplete.
     */
    public static Task fromFileString(String fileString) throws CorruptedFileException, IncompleteDataException {
        //for importing data from saved file
        String[] parts = fileString.split("\\s*\\|\\s*");
        if (parts.length >= 3) {
            String taskType = parts[0].trim();
            String isDoneSymbol = parts[1].trim();
            String description = parts[2].trim();

            boolean isDone = isDoneSymbol.equals("1");

            switch (taskType) {
                case "T":
                    try {
                        return new ToDo(description, isDone);
                    } catch (IncompleteDataException e) {
                        throw new IncompleteDataException("Incomplete data in ToDo task");
                    }
                case "D":
                    if (parts.length >= 4) {
                        String by = parts[3].trim();
                        try{
                            return new Deadline(description, by, isDone);
                        } catch (IncompleteDataException e){
                            throw new IncompleteDataException("Incomplete data in Deadline task");
                        }

                    } else {
                        throw new IncompleteDataException("Missing information in Deadline task");
                    }
                case "E":
                    if (parts.length >= 5) {
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        try {
                            return new Event(description, from, to, isDone);
                        } catch (IncompleteDataException e){
                            throw new IncompleteDataException("Incomplete data in Event task");
                        }
                    } else {
                            throw new IncompleteDataException("Missing information in Event task");
                    }

                default:
                    throw new CorruptedFileException("Invalid task type"); // Invalid task type
            }
        }
        throw new CorruptedFileException("Invalid fileString format"); // Invalid fileString format
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}


