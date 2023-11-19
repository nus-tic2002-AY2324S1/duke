package Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the Task class.
 */
public class TaskTest {

    /**
     * Tests the constructor of the Task class.
     * Verifies that the task description and status are set correctly.
     */
    @Test
    public void testTaskConstructor() {
        Task task = new Task("Sample Task", false);
        assertEquals("Sample Task", task.getTaskDescription());
        assertEquals("Not Done", task.getIsDone());
    }

    /**
     * Tests the markDone method of the Task class.
     * Verifies that the task is marked as done after calling markDone.
     */
    @Test
    public void testMarkDone() {
        Task task = new Task("Sample Task", false);
        task.markDone();
        assertEquals("Done", task.getIsDone());
    }

    /**
     * Tests the unmarkDone method of the Task class.
     * Verifies that the task is marked as not done after calling unmarkDone.
     */
    @Test
    public void testUnmarkDone() {
        Task task = new Task("Sample Task", true);
        task.unmarkDone();
        assertEquals("Not Done", task.getIsDone());
    }

    /**
     * Tests the toString method of the Task class.
     * Verifies that the toString representation is as expected.
     */
    @Test
    public void testToString() {
        Task task = new Task("Sample Task", false);
        assertEquals("[Todo] [Not Done] Sample Task", task.toString());
    }

    /**
     * Tests the fromString method of the Task class.
     * Verifies that a Task object can be created from a file string.
     */
    @Test
    public void testFromFileString() {
        String fileString = "Task | false | Sample Task";
        Task task = Task.fromString(fileString);
        assertNotNull(task);
        assertEquals("Sample Task", task.getTaskDescription());
        assertEquals("Not Done", task.getIsDone());
    }

    /**
     * Tests the toFileString method of the Task class.
     * Verifies that the task can be represented as a file string.
     */
    @Test
    public void testToFileString() {
        Task task = new Task("Sample Task", false);
        assertEquals("Task | false | Sample Task", task.toFileString());
    }
}
