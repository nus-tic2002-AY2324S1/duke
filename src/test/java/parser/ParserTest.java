import org.junit.jupiter.api.Test;
import constants.ErrorMessages;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import parser.Parser;
import exceptions.DukeException;

public class ParserTest {
    @Test
    public void parseTest(){
        Throwable exception; 

        // Test Case 1: Invalid User Command
        exception = assertThrows(DukeException.class, () -> Parser.parse("ghjkl "));
        assertEquals(ErrorMessages.INVALID_USER_INPUT, exception.getMessage());

        exception = assertThrows(DukeException.class, () -> Parser.parse("bYs"));
        assertEquals(ErrorMessages.INVALID_USER_INPUT, exception.getMessage());
    }

}
