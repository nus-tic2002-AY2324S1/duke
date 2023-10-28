package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void getMaxDayOfMonth_returnMaxDayOfTheMonth() {
        int day = Parser.getMaxDayOfMonth(2023, 2);
        assertEquals(28, day);
    }

    @Test
    public void dateValidation_wrongMonth_exceptionThrown() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> Parser.dateValidation(2023, -1, 1));
    }

    @Test
    public void dateValidation_wrongDay_exceptionThrown() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> Parser.dateValidation(2023, 2, 29));
    }

    @Test
    public void timeValidation_wrongHour_exceptionThrown() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> Parser.timeValidation(24, 0));
    }

    @Test
    public void timeValidation_wrongMinute_exceptionThrown() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> Parser.timeValidation(23, 60));
    }

    @Test
    public void parseKeyword_returnCorrectKeyword() {
        String keyword = Parser.parseKeyword("todo  //return book");
        assertEquals("todo", keyword);
    }

    @Test
    public void parseArgument_returnCorrectKeyword() {
        String argument = Parser.parseArgument("todo  //return book");
        assertEquals("//return book", argument);
    }

    @Test
    public void parseKeywordToCommand_eventKeyword_returnCorrectCommand() {
        Command command = Parser.parseKeywordToCommand(new UserKeywordArgument("event return book"));
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parseKeywordToCommand_todoKeyword_returnCorrectCommand() {
        Command command = Parser.parseKeywordToCommand(new UserKeywordArgument("todo return book"));
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void parseKeywordToCommand_invalidKeyword_returnNull() {
        Command command = Parser.parseKeywordToCommand(new UserKeywordArgument("what return book"));
        assertEquals(null, command);
    }

}
