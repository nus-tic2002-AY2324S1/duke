package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
