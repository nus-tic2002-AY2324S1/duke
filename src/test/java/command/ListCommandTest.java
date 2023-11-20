package command;

import io.*;
import org.junit.jupiter.api.Test;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    @Test
    void handleCommand() {
        Todo todo = new Todo("test description");
        List<Task> taskList = new ArrayList<>();
        taskList.add(todo);
        Event event = new Event("test description", "2023/10/20 2359");
        taskList.add(event);
        CrabyMessage.checkListName = "test";
        ListCommand listCommand = new ListCommand();
        listCommand.handleCommand("list", taskList);
        assertAll("list", () -> assertEquals(2, taskList.size()),
                () -> assertEquals("[T][҉҉҉] Test description", taskList.get(0).toString()),
                () -> assertEquals("[E][҉҉҉] Test description || from: 20 Oct, Fri 11:59PM",
                        taskList.get(1).toString()));
    }
}