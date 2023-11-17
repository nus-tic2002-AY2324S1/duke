package duke.command;

import java.time.LocalDateTime;
import java.util.List;

import duke.filehandler.FileStorage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents a command to add a Deadline task to the task list.
 */
public class AddDeadlineCommand extends AddTaskCommand {

    // The due date and time for Deadline Task
    private final LocalDateTime taskDueDate;

    /**
     * Constructs an `AddDeadlineCommand` with the specified task name and due date.
     *
     * @param taskName    The name of the deadline task.
     * @param taskDueDate The due date and time of the deadline task.
     */
    public AddDeadlineCommand(String taskName, LocalDateTime taskDueDate) {

        super(taskName);
        this.taskDueDate = taskDueDate;
    }

    /**
     * Executes the command by creating a `DeadlineTask` and adding it to the task list.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param display     The message display interface to show messages to the user.
     * @param taskList    The list of tasks to which the new Deadline task will be added.
     */
    @Override
    public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {
        assert !taskName.isEmpty() : "Task name can't be blank!";
        // Create a `DeadlineTask` with the specified task name and task due date
        Task task = new DeadlineTask(taskName, taskDueDate);
        // Add the created `DeadlineTask` to the task list
        addTask(fileStorage, display, taskList, task);
    }

}
