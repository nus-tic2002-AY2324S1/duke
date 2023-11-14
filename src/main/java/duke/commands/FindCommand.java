package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    String keyword;
    ArrayList<Task> userTasks;

    public FindCommand(String line) throws DukeException {
        if (line.split(" ").length > 2) {
            throw new DukeException("Please provide only one keyword at a time!");
        } else {
            this.keyword = line.split(" ")[1].toLowerCase();
        }

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
