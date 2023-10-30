package duke.command;

import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends AddTaskCommand {

  /**
   * Constructs an `AddTodoCommand` with the specified task name.
   *
   * @param taskName The name of the todo task.
   */
  public AddTodoCommand(String taskName) {

    super(taskName);
  }

  /**
   * Executes the command by creating a `TodoTask` and adding it to the task list.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of tasks to which the new todo task will be added.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {
    // Create a `TodoTask` with the specified name
    Task task = new TodoTask(taskName);
    // Add the created todo task to the task list
    addTask(display, taskList, task);
  }

}
