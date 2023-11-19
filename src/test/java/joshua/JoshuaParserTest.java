package joshua;

import commands.Command;
import commands.DeadlineCommand;
import commands.EventCommand;
import commands.TodoCommand;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JoshuaParserTest {
    @Test
    public void test_parseTodoCommand() {
        JoshuaParser parser = new JoshuaParser();
        String desc = "read book";
        TodoCommand expected = new TodoCommand(desc);
        TodoCommand actual = (TodoCommand) parser.parse("todo read book");

        assertEquals(expected.getTodoToAdd().toString(), actual.getTodoToAdd().toString());
    }

    @Test
    public void test_parseDeadlineCommand() {
        JoshuaParser parser = new JoshuaParser();
        String desc = "return book";
        DeadlineCommand expected = new DeadlineCommand(desc, "9 Sep 2023, 9:45 am");
        DeadlineCommand actual = (DeadlineCommand) parser.parse("deadline return book /by 9/9/2023 0945");

        assertEquals(expected.getDeadlineToAdd().toString(), actual.getDeadlineToAdd().toString());
    }

    @Test
    public void test_parseEventCommand() {
        JoshuaParser parser = new JoshuaParser();
        EventCommand expected = new EventCommand("annual bake sale", "9 Sep 2023, 9:45 am", "11 Sep 2023, 7:00 pm");
        EventCommand actual = (EventCommand) parser.parse("event annual bake sale /from 9/9/2023 0945 /to 11/9/2023 1900");

        assertEquals(expected.getEventToAdd().toString(), actual.getEventToAdd().toString());
    }

    @Test
    public void test_parseDateTime() {
        JoshuaParser parser = new JoshuaParser();

        try {
            assertEquals("9 Sep 2023, 09:45 am", parser.parseDateTime("9/9/2023 0945"));
            assertEquals("9 Sep 2023, 09:45 am", parser.parseDateTime("09/09/2023 0945"));
        } catch (InvalidCommandException e) {
            return;
        }
    }

    @Test
    public void test_parseDateTime_invalidCommandException() {
        JoshuaParser parser = new JoshuaParser();

        // Invalid date format
        assertThrows(InvalidCommandException.class, () -> parser.parseDateTime("9/239/2023 0945"));
        assertThrows(InvalidCommandException.class, () -> parser.parseDateTime("32/9/2023 1800"));
        // Invalid time format
        assertThrows(InvalidCommandException.class, () -> parser.parseDateTime("9/9/2023 2930"));
        assertThrows(InvalidCommandException.class, () -> parser.parseDateTime("09/09/2023 945"));
        assertThrows(InvalidCommandException.class, () -> parser.parseDateTime("09/09/2023 9:45"));
    }
}
