package seedu.duke.task;

import seedu.duke.commands.Command;

public class Todo extends Task {

    public Todo(String description) {
        super(Command.TODO, "T", description);
    }

    @Override
    public String getStatusMsg(int idx) {
        return super.getStatusMsg(idx);
    }
}