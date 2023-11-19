import Parse.ParsedCommand;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the ParsedCommand class.
 */
public class ParsedCommandTest {

    /**
     * Tests the constructor for basic commands like "list", "bye", "help".
     */
    @Test
    public void testBasicCommandConstructor() {
        ParsedCommand parsedCommand = new ParsedCommand("list");
        assertEquals("list", parsedCommand.getCommandType());
        assertNull(parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }

    /**
     * Tests the constructor for commands like "todo", "find", and "save".
     */
    @Test
    public void testTaskCommandConstructor() {
        ParsedCommand parsedCommand = new ParsedCommand("todo", "Sample Task");
        assertEquals("todo", parsedCommand.getCommandType());
        assertEquals("Sample Task", parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }

    /**
     * Tests the constructor for the "finddate" command.
     */
    @Test
    public void testFindDateCommandConstructor() {
        LocalDateTime findDate = LocalDateTime.now();
        ParsedCommand parsedCommand = new ParsedCommand("finddate", findDate);
        assertEquals("finddate", parsedCommand.getCommandType());
        assertNull(parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertEquals(findDate, parsedCommand.getFindDate());
    }

    /**
     * Tests the constructor for commands like "mark/unmark" or "delete".
     */
    @Test
    public void testIndexCommandConstructor() {
        ParsedCommand parsedCommand = new ParsedCommand("mark", 1);
        assertEquals("mark", parsedCommand.getCommandType());
        assertNull(parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(1, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }

    /**
     * Tests the constructor for the "deadline" command.
     */
    @Test
    public void testDeadlineCommandConstructor() {
        LocalDateTime deadline = LocalDateTime.now();
        ParsedCommand parsedCommand = new ParsedCommand("deadline", "Sample Deadline", deadline);
        assertEquals("deadline", parsedCommand.getCommandType());
        assertEquals("Sample Deadline", parsedCommand.getTaskDescription());
        assertEquals(deadline, parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }

    /**
     * Tests the constructor for the "event" command.
     */
    @Test
    public void testEventCommandConstructor() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusHours(2);
        ParsedCommand parsedCommand = new ParsedCommand("event", "Sample Event", startDate, endDate);
        assertEquals("event", parsedCommand.getCommandType());
        assertEquals("Sample Event", parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertEquals(startDate, parsedCommand.getStartDate());
        assertEquals(endDate, parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }

}
