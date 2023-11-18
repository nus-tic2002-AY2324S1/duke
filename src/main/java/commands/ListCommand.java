package commands;

import java.util.ArrayList;
import exceptions.EmptyListException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;

public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} lists every {@code task} in the user's {@code tasks}
     * list.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            ArrayList<Task> list = tasks.listItems();
            ui.showTaskList(list);
        } catch (EmptyListException e) {
            ui.showError(e.getMessage());
        }
    }

}
