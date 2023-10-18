package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;


class DeleteCommandTest {

    @Test
    void handleCommand() {
        AddTaskCommand addTaskCommand = new AddTaskCommand();
        List<Task> tasks = new ArrayList<>();
        addTaskCommand.handleCommand("todo test", tasks);
        addTaskCommand.handleCommand("deadline test /by 2020-08-25", tasks);
        addTaskCommand.handleCommand("event test /at 2020-08-25", tasks);
        addTaskCommand.handleCommand("input", tasks);
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.handleCommand("delete 1", tasks);
        Assertions.assertEquals(3, tasks.size());
        deleteCommand.handleCommand("      delete          all", tasks);
        Assertions.assertEquals(0, tasks.size());
    }
}