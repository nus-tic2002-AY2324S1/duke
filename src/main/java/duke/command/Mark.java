package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Mark extends IndexBaseCommand {
    protected boolean isMark;
    private int index;

    public Mark(){
        this.isMark = true;
    }
    public Mark(String arguments){
        this.isMark = true;
//        process(arguments);
    }

    @Override
    public String message() {
        return "Nice! I've marked this task as done:";
    }

    public boolean isMark(){
        return isMark;
    }

}
