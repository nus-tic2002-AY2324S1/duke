package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

  @Test
  public void testToStringNotCompleted() {

    Task task = new TodoTask("Test Task");
    String expected = "[T][ ] Test Task";
    assertEquals(expected, task.toString());
  }

  @Test
  public void testToStringCompleted() {

    Task task = new TodoTask("Test Task", true);
    String expected = "[T][X] Test Task";
    assertEquals(expected, task.toString());
  }

  @Test
  public void testMarkAsCompleted() {

    Task task = new TodoTask("Test Task");
    assertFalse(task.isCompleted());
    task.markAsCompleted();
    assertTrue(task.isCompleted());
  }

  @Test
  public void testMarkAsNotCompleted() {

    Task task = new TodoTask("Test Task", true);
    assertTrue(task.isCompleted());
    task.markAsNotCompleted();
    assertFalse(task.isCompleted());
  }

  @Test
  public void testGetTaskName() {

    Task task = new TodoTask("Test Task");
    assertEquals("Test Task", task.getTaskName());
  }

  @Test
  public void testGetTaskType() {

    Task task = new TodoTask("Test Task");
    assertEquals('T', task.getTaskType());
  }

  @Test
  public void testIsCompleted() {

    Task task = new TodoTask("Test Task");
    assertFalse(task.isCompleted());
  }

  @Test
  public void testDateTimetoString() {

    Task task = new TodoTask("Test Task");
    LocalDateTime dateTime = LocalDateTime.of(2023, 10, 31, 12, 0);
    String expected = "Tue 31 Oct 2023 12:00";
    assertEquals(expected, task.dateTimetoString(dateTime));
  }

  @Test
  public void testToFile() {

    Task task = new TodoTask("Test Task");
    String expected = "T | 0 | Test Task";
    assertEquals(expected, task.toFile());
  }

}
