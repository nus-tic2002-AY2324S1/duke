package duke.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalDate;
public class EventTaskTest {

  @Test
  public void testToString() {
    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "[E][ ] Test Event (from: Tue 31 Oct 2023 12:00 to: Tue 31 Oct 2023 14:00)";
    assertEquals(expected, task.toString());
  }

  @Test
  public void testToFile() {
    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "E | 0 | Test Event | 2023-10-31 12:00 | 2023-10-31 14:00";
    assertEquals(expected, task.toFile());
  }

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

  @Test
  public void testGetTaskFromString() {
    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "Tue 31 Oct 2023 12:00";
    assertEquals(expected, task.getTaskFromString());
  }

  @Test
  public void testGetTaskToString() {
    LocalDateTime from = LocalDateTime.of(2023, 10, 31, 12, 0);
    LocalDateTime to = LocalDateTime.of(2023, 10, 31, 14, 0);
    EventTask task = new EventTask("Test Event", from, to);
    String expected = "Tue 31 Oct 2023 14:00";
    assertEquals(expected, task.getTaskToString());
  }
}
