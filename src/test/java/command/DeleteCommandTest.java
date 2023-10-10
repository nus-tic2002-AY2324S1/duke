package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    @Test
    void handleCommand() {
        AddTaskCommand addTaskCommand = new AddTaskCommand();
        List<Task> tasks = new ArrayList<>();
        addTaskCommand.handleCommand("input", tasks);
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.handleCommand("delete 1", tasks);
        Assertions.assertEquals(0, tasks.size());
    }
}