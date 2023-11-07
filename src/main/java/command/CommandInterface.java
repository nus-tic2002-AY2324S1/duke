package command;

import task.Task;

import java.util.List;

/**
 * CommandInterface is an interface that handles the command.
 * It has a method to handle the command.
 */
public interface CommandInterface {

    /**
     * Sends the input command to the handleCommand method.
     * This method is to handle any command from the user.
     *
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    void handleCommand(String input, List<Task> tasks);
}
