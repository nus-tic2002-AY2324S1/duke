package duke.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DeadlineTaskTest {

  @Test
  public void testToString() {
    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);
    String expected = "[D][ ] Test Deadline (by: Tue 31 Oct 2023 12:00)";
    assertEquals(expected, task.toString());
  }

  @Test
  public void testToFile() {
    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);
    String expected = "D | 0 | Test Deadline | 2023-10-31 12:00";
    assertEquals(expected, task.toFile());
  }

  @Test
  public void testCheckDate() {
    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);

    // Check with a date that matches the due date
    LocalDate checkedDate = LocalDate.of(2023, 10, 31);
    assertTrue(task.checkDate(checkedDate));

    // Check with a date that doesn't match the due date
    LocalDate differentDate = LocalDate.of(2023, 11, 1);
    assertFalse(task.checkDate(differentDate));
  }

  @Test
  public void testGetTaskDueDateString() {
    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);
    String expected = "Tue 31 Oct 2023 12:00";
    assertEquals(expected, task.getTaskDueDateString());
  }
}
