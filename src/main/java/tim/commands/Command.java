package tim.commands;

import tim.util.TaskList;

public abstract class Command {
    public abstract void execute(String[] token, TaskList tasks);
}
