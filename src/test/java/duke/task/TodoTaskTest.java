package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests the functionality of the TodoTask class.
 */
public class TodoTaskTest {

  /**
   * Test converting an incomplete TodoTask to its string representation.
   */
  @Test
  public void testToString() {
    TodoTask task = new TodoTask("Test Todo");
    String expected = "[T][ ] Test Todo";
    assertEquals(expected, task.toString());
  }

  /**
   * Test converting a TodoTask to its string format for storing in a file.
   */
  @Test
  public void testToFile() {
    TodoTask task = new TodoTask("Test Todo");
    String expected = "T | 0 | Test Todo";
    assertEquals(expected, task.toFile());
  }

  /**
   * Test checking if a TodoTask matches a specific date.
   * Since TodoTasks do not have associated dates, it should always return false.
   */
  @Test
  public void testCheckDate() {
    TodoTask task = new TodoTask("Test Todo");
    // Check with any date, should always return false
    assertFalse(task.checkDate(null));
  }
}
