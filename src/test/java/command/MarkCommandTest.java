package command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkCommandTest {

    @Test
    void handleCommand() {

        Todo todo = new Todo("test description");
        Deadline deadline = new Deadline("test", "2023/10/20 2359");
        List<Task> taskList = new ArrayList<>();
        taskList.add(todo);
        taskList.add(deadline);
        MarkCommand markCommand = new MarkCommand();
        markCommand.handleCommand("mark 1", taskList);
        assertTrue(todo.getDone());
        assertFalse(deadline.getDone());
    }
}

