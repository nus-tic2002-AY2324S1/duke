package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseListCommand() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseByeCommand() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void testParseDeleteCommand() throws DukeException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseMarkCommand() throws DukeException {
        Command command = Parser.parse("mark 2");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseUnmarkCommand() throws DukeException {
        Command command = Parser.parse("unmark 3");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void testParseTodoCommand() throws DukeException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof NewTaskCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws DukeException {
        Command command = Parser.parse("deadline Finish report /by 30/11/2023 2000");
        assertTrue(command instanceof NewTaskCommand);
    }

    @Test
    public void testParseEventCommand() throws DukeException {
        Command command = Parser.parse("event Attend meeting /from 10/11/2023 /to 20/11/2023");
        assertTrue(command instanceof NewTaskCommand);
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(DukeException.class, () -> Parser.parse("invalid"));
    }
}
