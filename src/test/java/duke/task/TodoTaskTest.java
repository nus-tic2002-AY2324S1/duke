package duke.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TodoTaskTest {

  @Test
  public void testToString() {
    TodoTask task = new TodoTask("Test Todo");
    String expected = "[T][ ] Test Todo";
    assertEquals(expected, task.toString());
  }

  @Test
  public void testToFile() {
    TodoTask task = new TodoTask("Test Todo");
    String expected = "T | 0 | Test Todo";
    assertEquals(expected, task.toFile());
  }

  @Test
  public void testCheckDate() {
    TodoTask task = new TodoTask("Test Todo");

    // Check with any date, should always return true
    assertFalse(task.checkDate(null));
  }
}
