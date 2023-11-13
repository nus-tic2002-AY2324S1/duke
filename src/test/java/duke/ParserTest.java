import org.junit.jupiter.api.Test;

import duke.Parser;
import exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseTest(){
        Throwable exception; 

        // Test Case 1: Invalid User Command
        exception = assertThrows(DukeException.class, () -> Parser.parse("ghjkl "));
        assertEquals("Oops! Look at the manual to see accepted inputs!", exception.getMessage());

        exception = assertThrows(DukeException.class, () -> Parser.parse("bYs"));
        assertEquals("Oops! Look at the manual to see accepted inputs!", exception.getMessage());
    }

}
