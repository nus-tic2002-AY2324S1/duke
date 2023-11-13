package TaskClasses;

import ExceptionClasses.*;

import ExceptionClasses.CorruptedFileException;

public class Task {

    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) throws IncompleteDataException {
        if (description == null || description.isEmpty()) {
            throw new IncompleteDataException();
        }

        this.description = description;
        this.isDone = isDone;
    }


    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public String toFileString() {
        String isDoneSymbol = isDone ? "1" : "0";
        return "T | " + isDoneSymbol + " | " + description;
    }
    protected boolean getIsDone() {
        return isDone;
    }

    public static Task fromFileString(String fileString) throws CorruptedFileException, IncompleteDataException {
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


