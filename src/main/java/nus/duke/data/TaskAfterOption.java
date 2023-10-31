package nus.duke.data;

import java.time.LocalDateTime;

/**
 * The `TaskAfterOption` class represents an option for specifying a task or a date/time
 * after which another task or event should occur. This option is used in certain commands
 * to indicate a dependency or a specific time reference.
 */
public class TaskAfterOption {
    private final DataType dataType;
    private final int taskNumber;
    private final LocalDateTime dateTime;

    /**
     * Instantiates a new `TaskAfterOption` specifying a task dependency.
     *
     * @param taskNumber The task number after which the dependent task should occur.
     */
    public TaskAfterOption(int taskNumber) {
        dataType = DataType.TASK;
        this.taskNumber = taskNumber;
        this.dateTime = null;
    }

    /**
     * Instantiates a new `TaskAfterOption` specifying a time reference.
     *
     * @param dateTime The date and time after which the event should occur.
     * @throws NullPointerException If the provided `dateTime` is null.
     */
    public TaskAfterOption(LocalDateTime dateTime) {
        assert dateTime != null;

        dataType = DataType.TIME;
        this.dateTime = dateTime;
        this.taskNumber = -1;
    }

    /**
     * Checks if this `TaskAfterOption` represents a task dependency.
     *
     * @return `true` if it represents a task dependency, `false` otherwise.
     */
    public boolean isAfterTask() {
        return dataType.equals(DataType.TASK);
    }

    /**
     * Checks if this `TaskAfterOption` represents a time reference.
     *
     * @return `true` if it represents a time reference, `false` otherwise.
     */
    public boolean isAfterTime() {
        return dataType.equals(DataType.TIME);
    }

    /**
     * Gets the task number after which the dependent task should occur.
     *
     * @return The task number or -1 if this `TaskAfterOption` represents a time reference.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Gets the date and time after which the event should occur.
     *
     * @return The date and time or null if this `TaskAfterOption` represents a task dependency.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private enum DataType {
        TASK,
        TIME
    }
}
