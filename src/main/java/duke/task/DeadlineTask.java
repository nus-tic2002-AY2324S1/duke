package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a `DeadlineTask` class that extends `Task` and represents a task with a deadline.
 */
public class DeadlineTask extends Task {

  protected LocalDateTime taskDueDate;

  /**
   * Constructs a `DeadlineTask` with the specified task name and due date.
   *
   * @param taskName    The name of the deadline task.
   * @param taskDueDate The due date and time of the deadline task.
   */
  public DeadlineTask(String taskName, LocalDateTime taskDueDate) {

    super('D', taskName);
    this.taskDueDate = taskDueDate;
  }

  /**
   * Constructs a `DeadlineTask` with the specified task name, completion status, and due date.
   *
   * @param taskName    The name of the deadline task.
   * @param completed   `true` if the task is completed, `false` otherwise.
   * @param taskDueDate The due date and time of the deadline task.
   */
  public DeadlineTask(String taskName, boolean completed, LocalDateTime taskDueDate) {

    super('D', taskName, completed);
    this.taskDueDate = taskDueDate;
  }

  /**
   * Gets the formatted due date of the task.
   *
   * @return The due date of the task as a formatted string.
   */
  private String getTaskDueDateString() {

    return dateTimetoString(taskDueDate);
  }

  /**
   * Gets the due date of the task.
   *
   * @return The due date of the task as a LocalDate Object.
   */
  public LocalDate getTaskDueDate() {

    return taskDueDate.toLocalDate();
  }

  /**
   * Checks if a given date matches the due date of the task.
   *
   * @param checkedDate The date to be checked.
   * @return `true` if the checked date matches the due date of the task, `false` otherwise.
   */
  @Override
  public boolean checkDate(LocalDate checkedDate) {

    return checkedDate.equals(getTaskDueDate());
  }

  /**
   * Converts the `DeadlineTask` to a string for display.
   *
   * @return A string representation of the `DeadlineTask`.
   */
  @Override
  public String toString() {

    return super.toString() + String.format(" (by: %s)", getTaskDueDateString());
  }

  /**
   * Converts the `DeadlineTask` to a string for saving to Duke Data File.
   *
   * @return A string representation of the `DeadlineTask` for saving to Duke Data File.
   */
  @Override
  public String toFile() {

    return super.toFile() + " | " + taskDueDate.toString();
  }

}
