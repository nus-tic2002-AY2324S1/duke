package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the EventTask class.
 */
public class EventTaskTest {

  /**
   * Test the conversion of an EventTask to its string representation.
   */
  @Test
  public void testToString() {

    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "[E][ ] Test Event (from: Tue 31 Oct 2023 12:00 to: Tue 31 Oct 2023 14:00)";
    assertEquals(expected, task.toString());
  }

  /**
   * Test the conversion of an EventTask to a file format string.
   */
  @Test
  public void testToFile() {

    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "E | 0 | Test Event | 2023-10-31 12:00 | 2023-10-31 14:00";
    assertEquals(expected, task.toFile());
  }

  /**
   * Test checking a given date against the start and end dates of the task.
   */
  @Test
  public void testCheckDate() {

    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);

    // Check with a date that matches the start date
    LocalDate checkedDateFrom = LocalDate.of(2023, 10, 31);
    assertTrue(task.checkDate(checkedDateFrom));

    // Check with a date that matches the end date
    LocalDate checkedDateTo = LocalDate.of(2023, 10, 31);
    assertTrue(task.checkDate(checkedDateTo));

    // Check with a date that doesn't match either the start or end date
    LocalDate differentDate = LocalDate.of(2023, 11, 1);
    assertFalse(task.checkDate(differentDate));
  }

  /**
   * Test getting the string representation of the start date of the event.
   */
  @Test
  public void testGetTaskFromString() {

    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "Tue 31 Oct 2023 12:00";
    assertEquals(expected, task.getTaskFromString());
  }

  /**
   * Test getting the string representation of the end date of the event.
   */
  @Test
  public void testGetTaskToString() {

    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "Tue 31 Oct 2023 14:00";
    assertEquals(expected, task.getTaskToString());
  }

}
