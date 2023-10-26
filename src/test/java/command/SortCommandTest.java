package command;

import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SortCommandTest {

    @Test
    void handleCommand() {
        AddTaskCommand addTaskCommand = new AddTaskCommand();
        List<Task> tasks = new ArrayList<>();
        addTaskCommand.handleCommand("todo test", tasks);
        addTaskCommand.handleCommand("check deadline test /by 2023-08-25", tasks);
        addTaskCommand.handleCommand("present /from 2020-04-20", tasks);
        addTaskCommand.handleCommand("input", tasks);
        SortCommand sortCommand = new SortCommand();
        sortCommand.handleCommand("sort date", tasks);
        assertTrue(tasks.get(0).toString().toLowerCase().contains("present"));
        sortCommand.handleCommand("sort type", tasks);
        assertTrue(tasks.get(0).toString().toLowerCase().contains("check"));

    }
}