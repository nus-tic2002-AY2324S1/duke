package duke.command;

import java.util.List;

import duke.dukeexceptions.DukeException;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents a base command in the Duke application.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param display     The message display interface to show messages to the user.
     * @param taskList    The list of tasks that the command will operate on.
     * @throws DukeException If an error occurs while executing the command.
     */
    void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList)
            throws DukeException;

    /**
     * Stores the task list for future loading of tasks.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param taskList    The list of tasks to be stored.
     */
    default void storeDuke(FileStorage fileStorage, List<Task> taskList) {
        fileStorage.store(taskList);
    }

}
