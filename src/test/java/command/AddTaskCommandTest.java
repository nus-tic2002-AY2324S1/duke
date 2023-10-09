package command;

import command.AddTaskCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class AddTaskCommandTest {

    @Test
    public void handleCommand(){
        AddTaskCommand addTaskCommand = new AddTaskCommand();
        List<Task> tasks = new ArrayList<>();
        addTaskCommand.handleCommand("input", tasks);
        Assertions.assertEquals(1, tasks.size());
    }
}
