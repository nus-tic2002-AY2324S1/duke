package duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetTaskType() {
        Deadline deadline = new Deadline("Test Deadline");
        assertEquals(TaskType.D, deadline.getTaskType());
    }

    @Test
    public void testGetDeadline() {
        LocalDateTime dueDateTime = LocalDateTime.now();
        Deadline deadline = new Deadline("Test Deadline", dueDateTime);
        assertEquals(dueDateTime, deadline.getDeadline());
    }

    @Test
    public void testSetDeadline() {
        LocalDateTime dueDateTime = LocalDateTime.now();
        Deadline deadline = new Deadline("Test Deadline");
        deadline.setDeadline(dueDateTime);
        assertEquals(dueDateTime, deadline.getDeadline());
    }

    @Test
    public void testStatusIconForUndoneDeadline() {
        Deadline deadline = new Deadline("Test Deadline");
        assertEquals("[D][ ]", deadline.getStatusIcon());
    }

    @Test
    public void testStatusIconForDoneDeadline() {
        Deadline deadline = new Deadline("Test Deadline");
        deadline.markAsDone();
        assertEquals("[D][X]", deadline.getStatusIcon());
    }
}
