package commands;

import java.util.ArrayList;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    protected String keyword;
    protected ArrayList<Task> userTasks;

    public FindCommand(String line) throws DukeException {
        if(!Pattern.matches(RegExp.FIND_COMMAND_FORMAT_REGEX, line)){
            throw new DukeException(ErrorMessages.INVALID_FIND_COMMAND_FORMAT);
        }

        this.keyword = line.split(RegExp.SPACE_DELIMITER)[1].toLowerCase().trim();
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
