package commands;

import duke.Storage;
import duke.UI;
import tasks.TaskList;

public class Command {
    protected boolean isExit = false;
    protected String input;

    //abstract void execute(TaskList tasks , UI ui, Storage storage);
    public void execute(TaskList tasks, UI ui, Storage storage){
    }

    public Boolean isExit(){
        return this.isExit;
    }
}
