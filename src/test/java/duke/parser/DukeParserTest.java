package duke.parser;

import duke.dukeexceptions.InvalidNumberFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functionality of the DukeParser class.
 */
public class DukeParserTest {

  private DukeParser dukeParser;

  /**
   * Set up the test environment before each test case.
   */
  @BeforeEach
  public void setUp() {

    dukeParser = new DukeParser();
  }

  /**
   * Test parsing a date and time string into a LocalDateTime object.
   */
  @Test
  public void testParseDateTime() {

    LocalDateTime dateTime = DukeParser.parseDateTime("2023-10-31 08:00");
    assertEquals(LocalDateTime.of(2023, 10, 31, 8, 0), dateTime);
  }

  /**
   * Test parsing a date string into a LocalDate object.
   */
  @Test
  public void testParseDate() {

    LocalDate date = DukeParser.parseDate("2023-10-31");
    assertEquals(LocalDate.of(2023, 10, 31), date);
  }

  /**
   * Test parsing a date and time string into a LocalDateTime object, with a fallback to midnight if the time is not provided.
   */
  @Test
  public void testParseDateTimeOrDate() {

    LocalDateTime dateTime = DukeParser.parseDateTimeOrDate("2023-10-31 08:00");
    assertEquals(LocalDateTime.of(2023, 10, 31, 8, 0), dateTime);

    LocalDateTime date = DukeParser.parseDateTimeOrDate("2023-10-31");
    assertEquals(LocalDateTime.of(2023, 10, 31, 0, 0), date);
  }

  /**
   * Test parsing an integer from a string.
   */
  @Test
  public void testParseInteger() throws InvalidNumberFormatException {

    Integer integer = DukeParser.parseInteger("42");
    assertEquals(42, integer.intValue());
  }

  /**
   * Test parsing a command from user input.
   */
  @Test
  public void testParseCommandFromInput() {

    String command = DukeParser.parseCommandFromInput("delete 1");
    assertEquals(command, "delete");
  }

}
