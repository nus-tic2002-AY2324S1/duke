package duke.command;

import java.time.LocalDateTime;
import java.util.List;

import duke.dukeexceptions.DukeException;
import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents a `RescheduleCommand` to reschedule a task by extending its end date.
 */
public class RescheduleCommand extends Command {

    // The index of the task to be rescheduled.
    private final int itemIndex;
    private final LocalDateTime revisedDateTime;

    /**
     * Constructs a `RescheduleCommand` with the specified item index to reschedule a task.
     *
     * @param itemIndex The index of the task to be rescheduled.
     */
    public RescheduleCommand(int itemIndex, LocalDateTime revisedDateTime) {

        this.revisedDateTime = revisedDateTime;
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the command by extending the end date of the specified task in the task list.
     *
     * @param fileStorage The file storage handler for saving tasks to a file.
     * @param display     The message display interface to show messages to the user.
     * @param taskList    The list of tasks containing the task to be rescheduled.
     */
    @Override
    public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList)
            throws DukeException {

        taskList.get(itemIndex).changeEndDate(revisedDateTime);
        storeDuke(fileStorage, taskList);
        display.rescheduleMessage(taskList.get(itemIndex));
    }

}
