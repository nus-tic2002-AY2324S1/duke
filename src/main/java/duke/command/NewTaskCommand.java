package duke.command;

import duke.Utils;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.UI;

import java.io.IOException;

/**
 * Represent a command to create and add a new task to the task list.
 */
public class NewTaskCommand extends Command{
    String fullCommand;

    /**
     * Constructs a NewTaskCommand with the given full command.
     *
     * @param fullCommand The full command input by the user.
     */
    public NewTaskCommand(String fullCommand) { this.fullCommand =fullCommand; }


    /**
     * Executes the new task command, parsing the full command and creating a new task based on its type
     * (Todo, Deadline, or Event). The new task is then added to the task list and saved to storage.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for input and output.
     * @param storage  The storage for reading and writing data.
     * @throws DukeException If there is an issue with the command or task creation.
     * @throws IOException    If there is an issue with saving the task list.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {

        Task newTask = null;
        if(fullCommand.toLowerCase().contains("todo")){
            newTask = Utils.newTodoTask(fullCommand);
        } else if (fullCommand.toLowerCase().contains("deadline")) {
            newTask = Utils.newDeadlineTask(fullCommand);
        } else if (fullCommand.toLowerCase().contains("event")) {
            newTask = Utils.newEventTask(fullCommand);
        } else {
            UI.showError("Invalid command: " + fullCommand);
            return;
        }
        taskList.addTask(newTask);
        storage.save(taskList);
        UI.showNewTask(newTask, taskList);
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return Always returns false, as creating a new task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
