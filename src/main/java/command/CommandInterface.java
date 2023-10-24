package command;

import task.Task;

import java.util.List;

/**
 * CommandInterface is an interface that handle the command.
 * It has a method to handle the command.
 */
public interface CommandInterface {
    void handleCommand(String input, List<Task> tasks);
}
