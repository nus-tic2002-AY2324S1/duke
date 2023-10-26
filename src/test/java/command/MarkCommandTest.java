package command;

import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkCommandTest {

    @Test
    void handleCommand() {

        Todo todo = new Todo("test description");
        List<Task> taskList = new ArrayList<>();
        taskList.add(todo);
        MarkCommand markCommand = new MarkCommand();
        markCommand.handleCommand("mark 1", taskList);
        assertTrue(todo.getIsDone());
    }
}