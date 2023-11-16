import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.DukeException;
import commands.DueCommand;
import constants.ErrorMessages;
import constants.RegExp;

public class DueCommandTest {
    @Test
    public void dueCommandConstructorTest() throws DukeException{
        Throwable exception; 
        // Test Case 1: Missing Date
        exception = assertThrows(DukeException.class, () -> new DueCommand("due "));
        assertEquals(ErrorMessages.INVALID_DUE_COMMAND_FORMAT, exception.getMessage());

        // Test Case 2: Date in the wrong format
        exception = assertThrows(DukeException.class, () -> new DueCommand("due 10 Oct 2023"));
        assertEquals(ErrorMessages.INVALID_DUE_COMMAND_FORMAT, exception.getMessage());

        // Test Case 3: Invalid Date
        exception = assertThrows(DukeException.class, () -> new DueCommand("due 2023-02-56")); // invalid day
        assertEquals(ErrorMessages.INVALID_DUE_COMMAND_FORMAT, exception.getMessage());
        exception = assertThrows(DukeException.class, () -> new DueCommand("due 2023-21-06")); // invalid month
        assertEquals(ErrorMessages.INVALID_DUE_COMMAND_FORMAT, exception.getMessage());
        
        // Working Test 
        new DueCommand("due 2023-12-06");
        
    }
}
