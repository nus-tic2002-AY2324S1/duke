package command;

import org.junit.jupiter.api.Test;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAllCommandTest {

    @Test
    void handleCommand() {
        Todo todo = new Todo("test description");
        List<Task> taskList = new ArrayList<>();
        taskList.add(todo);
        Event event = new Event("test description", "2023/10/20 2359");
        taskList.add(event);
        System.out.println("Have " + taskList.size() + " tasks");
        DeleteAllCommand deleteAllCommand = new DeleteAllCommand();
        deleteAllCommand.handleCommand("deleteall", taskList);
        assertEquals(0, taskList.size());
    }
}