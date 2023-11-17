package duke.task;

import java.time.LocalDateTime;

/**
 * The `Events` class represents a task that has a starting and ending date and time.
 */
public class Event extends Task {
    private final TaskType taskType = TaskType.E;
    private LocalDateTime eventStartAt;
    private LocalDateTime eventEndAt;

    /**
     * Constructs an `Events` object with the specified description.
     *
     * @param description The description of the event task.
     */
    public Event(String description) {
        super(description);
    }


    /**
     * Constructs an `Events` object with the specified description, start date and time, and end date and time.
     *
     * @param description The description of the event task.
     * @param start       The starting date and time of the event.
     * @param end         The ending date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.eventStartAt = start;
        this.eventEndAt = end;
    }

    /**
     * Gets the type of the task, which is 'E' for Events.
     *
     * @return The task type.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Gets the starting date and time of the event.
     *
     * @return The starting date and time as a LocalDateTime.
     */
    public LocalDateTime getEventStartAt() {
        return eventStartAt;
    }

    /**
     * Sets the starting date and time of the event.
     *
     * @param start The new starting date and time to set.
     */
    public void setEventStartAt(LocalDateTime start) {
        this.eventStartAt = start;
    }

    /**
     * Gets the ending date and time of the event.
     *
     * @return The ending date and time as a LocalDateTime.
     */
    public LocalDateTime getEventEndAt() {
        return eventEndAt;
    }

    /**
     * Sets the ending date and time of the event.
     *
     * @param end The new ending date and time to set.
     */
    public void setEventEndAt(LocalDateTime end) {
        this.eventEndAt = end;
    }

    @Override
    public Task makeCopy() {
        Event copiedEvent = new Event(this.getDescription(), this.getEventStartAt(), this.getEventEndAt());
        if (this.getStatus()) {
            copiedEvent.markAsDone();
        }
        return copiedEvent;
    }

    @Override
    public String getStatusIcon() {
        return "[" + taskType + "]" + super.getStatusIcon();
    }
}
