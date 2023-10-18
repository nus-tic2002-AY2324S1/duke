package command;

import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UndoCommandTest {

    @Test
    void putInToStack() {
        List<Task> tasks = new ArrayList<>();
        UndoCommand.putInToStack("delete 1", tasks);
        UndoCommand.putInToStack("list", tasks);
        UndoCommand.putInToStack("undo", tasks);
        assertEquals(1, UndoCommand.stackTaskList.size());

    }

    @Test
    void handleCommand() {

    }
}