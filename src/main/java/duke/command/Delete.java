package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Delete extends IndexBaseCommand {

    public Delete(){}
    public Delete(String arguments){
        process(arguments);
    }
    @Override
    public String message() {
        return "Noted. I've removed this task:";
    }
}
