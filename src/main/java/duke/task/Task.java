package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.dukeexceptions.DueDatePastDateException;
import duke.dukeexceptions.EventDateException;

/**
 * Represents an abstract `Task` class for tasks in Duke.
 */
public abstract class Task {

    private final String taskName;
    private final Character taskType;
    private boolean completed;

    /**
     * Constructs a `Task` with the specified task type and name, initializing it as not completed.
     *
     * @param taskType The type of the task.
     * @param taskName The name of the task.
     */
    public Task(Character taskType, String taskName) {

        this.taskType = taskType;
        this.taskName = taskName;
        this.completed = false;
    }

    /**
     * Constructs a `Task` with the specified task type, name, and completion status.
     *
     * @param taskType    The type of the task.
     * @param taskName    The name of the task.
     * @param isCompleted `true` if the task is completed, `false` otherwise.
     */
    public Task(Character taskType, String taskName, boolean isCompleted) {

        this.taskType = taskType;
        this.taskName = taskName;
        this.completed = isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {

        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotCompleted() {

        this.completed = false;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {

        return taskName;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public Character getTaskType() {

        return taskType;
    }

    /**
     * Checks if the task is completed.
     *
     * @return `true` if the task is completed, `false` otherwise.
     */
    public boolean isCompleted() {

        return completed;
    }

    /**
     * Checks if the task matches a specific date.
     *
     * @param checkedDate The date to compare with the task's date (if applicable).
     * @return `true` if the task matches the date, `false` otherwise.
     */
    public boolean checkDate(LocalDate checkedDate) {

        return false; // Override this method in subclasses if the task has a date.
    }

    /**
     * This method allows you to change the end date of a task.
     *
     * @param taskDueDate The new end date for the task.
     */
    public void changeEndDate(LocalDateTime taskDueDate)
            throws DueDatePastDateException, EventDateException {

    }

    /**
     * Get the end date of the task.
     *
     * @return The end date of the task as a LocalDateTime object.
     */
    public LocalDateTime getTaskEndDate() {

        return LocalDateTime.now();
    }


    /**
     * Converts the `Task` to a string for display.
     *
     * @return A string representation of the `Task`.
     */
    public String toString() {

        String taskName = getTaskName();
        Character taskType = getTaskType();
        char taskComplete = isCompleted() ? 'X' : ' ';
        return "[" + taskType + "]" + "[" + taskComplete + "] " + taskName;
    }

    /**
     * Converts the date and time to a formatted string.
     *
     * @param dateTime The date and time to format.
     * @return The formatted date and time as a string.
     */
    public String dateTimetoString(LocalDateTime dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Converts the `Task` to a string for saving to a file.
     *
     * @return A string representation of the `Task` for saving to a file.
     */
    public String toFile() {

        String taskName = getTaskName();
        Character taskType = getTaskType();
        int taskComplete = isCompleted() ? 1 : 0;
        return taskType + " | " + taskComplete + " | " + taskName;
    }

}
