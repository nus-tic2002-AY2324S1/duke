package tim.body.commands;

import tim.body.TaskList;

public abstract class Command {
    public abstract void execute(String[] token, TaskList tasks);
}
