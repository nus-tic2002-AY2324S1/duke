package Duke;

import Duke.ExceptionClasses.DupeException;
import Duke.TaskClasses.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DupeTest {

    @Test
    public void testTaskToString() {
        // Create a task
        Task task = new Task("Sample Task");

        // Expected result
        String expected = "[T][ ] Sample Task";

        // Test
        assertEquals(expected, task.toString());
    }

}
