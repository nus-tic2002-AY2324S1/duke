package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        if (!taskList.getTaskList().isEmpty()){
            int idx = 1;
            UI.showMessage("Here are the tasks in your list: ");
            for (Task task :taskList.getTaskList()) {
                UI.showMessage(idx + ". "  + task.getStatusIcon() + " " + task.getDescription());
                idx++;
            }
        }else{
            UI.showMessage("list");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}