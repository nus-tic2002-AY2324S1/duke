package command;

import task.Task;

import java.util.List;

public interface CommandInterface {
    void handleCommand(String input, List<Task> tasks);
}
