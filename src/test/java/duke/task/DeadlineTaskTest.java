package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the DeadlineTask class.
 */
public class DeadlineTaskTest {

  /**
   * Test the conversion of a DeadlineTask to its string representation.
   */
  @Test
  public void testToString() {

    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);
    String expected = "[D][ ] Test Deadline (by: Tue 31 Oct 2023 12:00)";
    assertEquals(expected, task.toString());
  }

  /**
   * Test the conversion of a DeadlineTask to a file format string.
   */
  @Test
  public void testToFile() {

    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);
    String expected = "D | 0 | Test Deadline | 2023-10-31 12:00";
    assertEquals(expected, task.toFile());
  }

  /**
   * Test checking a given date against the due date of the task.
   */
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

  /**
   * Test getting the due date of the task as a string.
   */
  @Test
  public void testGetTaskDueDateString() {

    LocalDateTime dueDate = LocalDateTime.of(2023, 10, 31, 12, 0);
    DeadlineTask task = new DeadlineTask("Test Deadline", dueDate);
    String expected = "Tue 31 Oct 2023 12:00";
    assertEquals(expected, task.getTaskDueDateString());
  }

}
