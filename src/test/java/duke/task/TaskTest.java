package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the Task class and its subclasses (TodoTask, DeadlineTask, EventTask).
 */
public class TaskTest {

  /**
   * Test converting an incomplete task to its string representation.
   */
  @Test
  public void testToStringNotCompleted() {

    Task task = new TodoTask("Test Task");
    String expected = "[T][ ] Test Task";
    assertEquals(expected, task.toString());
  }

  /**
   * Test converting a completed task to its string representation.
   */
  @Test
  public void testToStringCompleted() {

    Task task = new TodoTask("Test Task", true);
    String expected = "[T][X] Test Task";
    assertEquals(expected, task.toString());
  }

  /**
   * Test marking a task as completed.
   */
  @Test
  public void testMarkAsCompleted() {

    Task task = new TodoTask("Test Task");
    assertFalse(task.isCompleted());
    task.markAsCompleted();
    assertTrue(task.isCompleted());
  }

  /**
   * Test marking a task as not completed.
   */
  @Test
  public void testMarkAsNotCompleted() {

    Task task = new TodoTask("Test Task", true);
    assertTrue(task.isCompleted());
    task.markAsNotCompleted();
    assertFalse(task.isCompleted());
  }

  /**
   * Test getting the name of a task.
   */
  @Test
  public void testGetTaskName() {

    Task task = new TodoTask("Test Task");
    assertEquals("Test Task", task.getTaskName());
  }

  /**
   * Test getting the type of a task.
   */
  @Test
  public void testGetTaskType() {

    Task task = new TodoTask("Test Task");
    assertEquals('T', task.getTaskType());
  }

  /**
   * Test checking if a task is completed or not.
   */
  @Test
  public void testIsCompleted() {

    Task task = new TodoTask("Test Task");
    assertFalse(task.isCompleted());
  }

  /**
   * Test converting a LocalDateTime to a string format.
   */
  @Test
  public void testDateTimetoString() {

    Task task = new TodoTask("Test Task");
    LocalDateTime dateTime = LocalDateTime.of(2023, 10, 31, 12, 0);
    String expected = "Tue 31 Oct 2023 12:00";
    assertEquals(expected, task.dateTimetoString(dateTime));
  }

  /**
   * Test converting a task to a string format for storing in a file.
   */
  @Test
  public void testToFile() {

    Task task = new TodoTask("Test Task");
    String expected = "T | 0 | Test Task";
    assertEquals(expected, task.toFile());
  }

}
