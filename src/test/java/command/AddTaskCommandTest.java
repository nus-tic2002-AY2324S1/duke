package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class AddTaskCommandTest {

    @Test
    public void handleCommand() {
        AddTaskCommand addTaskCommand = new AddTaskCommand();
        List<Task> tasks = new ArrayList<>();
        addTaskCommand.handleCommand("input", tasks);
        addTaskCommand.handleCommand("input1 /by tomorrow", tasks);
        addTaskCommand.handleCommand("input2 /from today /to sunday", tasks);
        Assertions.assertEquals(3, tasks.size());
    }
}
