package Parse;

import Parse.ParsedCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the Parser class.
 */
public class ParserTest {

    /**
     * Tests the parseCommand method with valid "list" command.
     */
    @Test
    public void testParseListCommand() {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = parser.parseCommand("list");
        assertEquals("list", parsedCommand.getCommandType());
        assertNull(parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }

    /**
     * Tests the parseCommand method with valid "bye" command.
     */
    @Test
    public void testParseByeCommand() {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = parser.parseCommand("bye");
        assertEquals("bye", parsedCommand.getCommandType());
        assertNull(parsedCommand.getTaskDescription());
        assertNull(parsedCommand.getDeadline());
        assertNull(parsedCommand.getStartDate());
        assertNull(parsedCommand.getEndDate());
        assertEquals(0, parsedCommand.getTaskIndex());
        assertNull(parsedCommand.getFindDate());
    }


    /**
     * Tests the checkTimeOrNot method with invalid input.
     */
    @Test
    public void testCheckTimeOrNotWithInvalidInput() {
        Parser parser = new Parser();
        Temporal result = parser.checkTimeOrNot("InvalidInput");
        assertNull(result);
    }

    /**
     * Tests the findIndex method with a valid keyword.
     */
    @Test
    public void testFindIndexWithValidKeyword() {
        Parser parser = new Parser();
        String[] wordArray = {"command", "parameter", "/by", "value"};
        int index = parser.findIndex(wordArray, "/by");
        assertEquals(2, index);
    }

    /**
     * Tests the findIndex method with an invalid keyword.
     */
    @Test
    public void testFindIndexWithInvalidKeyword() {
        Parser parser = new Parser();
        String[] wordArray = {"command", "parameter", "/by", "value"};
        int index = parser.findIndex(wordArray, "/at");
        assertEquals(-1, index);
    }

    // Add more test cases for other methods...

    /**
     * Tests the parseCommand method with invalid command.
     */
    @Test
    public void testParseInvalidCommand() {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = parser.parseCommand("invalidCommand");
        assertEquals("invalid", parsedCommand.getCommandType());
        assertEquals("Could not understand your command!", parsedCommand.getTaskDescription());
    }
}
