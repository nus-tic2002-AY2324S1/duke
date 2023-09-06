package seedu.duke.task;

import seedu.duke.commands.Command;

public class Todo extends Task {

    public Todo(String description) {
        super(Command.TODO, description);
    }

    @Override
    public String getStatusMsg() {
        return "[D]" + super.getStatusMsg();
    }
}