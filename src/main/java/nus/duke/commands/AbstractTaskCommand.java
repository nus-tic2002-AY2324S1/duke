package nus.duke.commands;

import nus.duke.data.TaskList;

/**
 * The `AbstractTaskCommand` class serves as the base class for commands that create tasks.
 */
public abstract class AbstractTaskCommand extends AbstractCommand {
    /**
     * Instantiates a new `AbstractTaskCommand` with the provided arguments.
     *
     * @param args The user input arguments.
     */
    public AbstractTaskCommand(String args) {
        super(args);
    }

    /**
     * Generates messages to confirm the addition of a task and provide task count information.
     *
     * @param tasks The task list containing the recently added task.
     * @return An array of strings representing messages confirming the added task and task count.
     */
    protected static String[] getTaskAddedMessages(TaskList tasks) {
        return new String[]{
                "Got it. I've added this task:",
                "  " + tasks.getLastTask(),
                String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }
}
