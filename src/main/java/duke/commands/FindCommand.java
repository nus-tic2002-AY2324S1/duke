package duke.commands;

import java.util.ArrayList;
import duke.constants.ErrorMessages;
import duke.constants.RegExp;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    String keyword;
    ArrayList<Task> userTasks;
    protected static final int INPUT_WORDS_REQUIRED = 2;

    public FindCommand(String line) throws DukeException {
        if (line.split(RegExp.SPACE_DELIMITER).length > INPUT_WORDS_REQUIRED) {
            throw new DukeException(ErrorMessages.INVALID_FIND_COMMAND_FORMAT);
        } 

        this.keyword = line.split(RegExp.SPACE_DELIMITER)[1].toLowerCase();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} finds all tasks with the given {@code keyword} attribute
     * and prints.
     * 
     * @param storage is not used in this implementation.
     */

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        userTasks = tasks.getTaskList();
        int counter = 0;

        for (Task t : userTasks) {
            String taskDesc = t.getDescription();
            if (taskDesc.contains(keyword)) {
                ui.printMessage(t.toString());
                counter++;
            }
        }

        if (counter == 0) {
            ui.printMessage("You have no tasks with this keyword: " + keyword);
        } else {
            ui.printMessage("You have a total of " + counter + " tasks with this keyword: " + keyword);
        }
    }
}
