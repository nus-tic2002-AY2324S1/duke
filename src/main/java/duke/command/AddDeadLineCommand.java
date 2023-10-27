package duke.command;

import duke.task.DeadlineTask;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Represents a command to add a Deadline task to the task list.
 */
public class AddDeadLineCommand extends AddTaskCommand {

  // The due date and time for Deadline Task
  private final LocalDateTime taskDueDate;

  /**
   * Constructs an `AddDeadLineCommand` with the specified task name and due date.
   *
   * @param taskName    The name of the deadline task.
   * @param taskDueDate The due date and time of the deadline task.
   */
  public AddDeadLineCommand(String taskName, LocalDateTime taskDueDate) {

    super(taskName);
    this.taskDueDate = taskDueDate;
  }

  /**
   * Executes the command by creating a `DeadlineTask` and adding it to the task list.
   */
  @Override
  public void execute() {
    // Create a `DeadlineTask` with the specified task name and task due date
    Task task = new DeadlineTask(taskName, taskDueDate);
    // Add the created `DeadlineTask` to the task list
    addTask(task);
  }

}
