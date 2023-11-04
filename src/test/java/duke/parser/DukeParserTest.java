package duke.parser;

import duke.dukeexceptions.InvalidNumberFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeParserTest {

  private DukeParser dukeParser;

  @BeforeEach
  public void setUp() {

    dukeParser = new DukeParser();
  }

  @Test
  public void testParseDateTime() {

    LocalDateTime dateTime = DukeParser.parseDateTime("2023-10-31 08:00");
    assertEquals(LocalDateTime.of(2023, 10, 31, 8, 0), dateTime);
  }

  @Test
  public void testParseDate() {

    LocalDate date = DukeParser.parseDate("2023-10-31");
    assertEquals(LocalDate.of(2023, 10, 31), date);
  }

  @Test
  public void testParseDateTimeOrDate() {

    LocalDateTime dateTime = DukeParser.parseDateTimeOrDate("2023-10-31 08:00");
    assertEquals(LocalDateTime.of(2023, 10, 31, 8, 0), dateTime);

    LocalDateTime date = DukeParser.parseDateTimeOrDate("2023-10-31");
    assertEquals(LocalDateTime.of(2023, 10, 31, 0, 0), date);
  }

  @Test
  public void testParseInteger() throws InvalidNumberFormatException {

    Integer integer = DukeParser.parseInteger("42");
    assertEquals(42, integer.intValue());
  }

  @Test
  public void testParseCommandFromInput() {

    String command = dukeParser.parseCommandFromInput("delete 1");
    assertEquals(command, "delete");

  }

}
