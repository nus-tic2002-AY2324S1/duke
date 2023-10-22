package Duke.Task;

import java.time.LocalDateTime;

/**
 * Represents an abstract `Task` class for tasks in Duke.
 */
public abstract class Task {
    private static int totalTasks = 0;
    private final String taskName;
    private final Character taskType;
    boolean completed;

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
        totalTasks++;
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
        totalTasks++;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return The total number of tasks.
     */
    public static int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Reduces the total count of tasks to represent removing a task.
     */
    public static void removeTask() {
        totalTasks--;
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
        String dayOfWeek = dateTime.getDayOfWeek().toString().substring(0, 3);
        String day = String.valueOf(dateTime.getDayOfMonth());
        String dayOfMonth = dateTime.getMonth().toString().substring(0, 3);
        String year = String.valueOf(dateTime.getYear());
        String hour = String.valueOf(dateTime.getHour());
        String minutes = String.valueOf(dateTime.getMinute());
        return dayOfWeek + " " + day + " " + dayOfMonth + " " + year + " " + hour + ":" + minutes;
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
