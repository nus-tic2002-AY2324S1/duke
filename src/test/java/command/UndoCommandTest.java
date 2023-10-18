package command;

import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UndoCommandTest {

    @Test
    void handleCommand() {
        List<Task> tasks = new ArrayList<>();

        AddTaskCommand addTaskCommand = new AddTaskCommand();
        DeleteCommand deleteCommand = new DeleteCommand();
        SortCommand sortByCommand = new SortCommand();
        MarkCommand markCommand = new MarkCommand();
        UndoCommand undoCommand = new UndoCommand();


        addTaskCommand.handleCommand("Test undo", tasks);
        addTaskCommand.handleCommand("Test undo 2", tasks);
        addTaskCommand.handleCommand("Test undo 3", tasks);
        addTaskCommand.handleCommand("Test undo 4", tasks);
        undoCommand.handleCommand("undo", tasks);
        assertEquals(3, tasks.size());

        deleteCommand.handleCommand("delete 1", tasks);
        undoCommand.handleCommand("undo", tasks);
        assertEquals(3, tasks.size());
        List<Task> expected = List.of(
                new Task("Test undo"),
                new Task("Test undo 2"),
                new Task("Test undo 3")
        );
        sortByCommand.handleCommand("sort type", tasks);
        undoCommand.handleCommand("undo", tasks);
        assertEquals(expected, tasks);

        markCommand.handleCommand("mark 1", tasks);
        undoCommand.handleCommand("undo", tasks);
        assertEquals(expected, tasks);

        undoCommand.handleCommand("undo", tasks);
        assertEquals(0, tasks.size());
    }
}