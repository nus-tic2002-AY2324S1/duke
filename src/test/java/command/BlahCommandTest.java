package command;

import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlahCommandTest {

    @Test
    void handleCommand() {
        List<Task> tasks = new ArrayList<>();
        BlahCommand blahCommand = new BlahCommand();
        blahCommand.handleCommand("input", tasks);
        assertEquals(0, tasks.size());
    }
}