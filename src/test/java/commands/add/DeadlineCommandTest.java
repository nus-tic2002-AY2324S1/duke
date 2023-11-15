import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.DukeException;
import commands.add.DeadlineCommand;
import constants.ErrorMessages;

public class DeadlineCommandTest {
    @Test
    public void deadlineCommandConstructorTest() throws DukeException{
        // Test Set Up
        Throwable exception;
         
        // Working Test Case
        DeadlineCommand a = new DeadlineCommand("deadline buy FOOD /by 2023-10-05");

        // Test Case 1: Missing /
        exception = assertThrows(DukeException.class, () -> new DeadlineCommand("deadline buy food by 2023-10-05 "));
        assertEquals(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT, exception.getMessage());

        // Test Case 2: Incorrect Date Format
        exception = assertThrows(DukeException.class, () -> new DeadlineCommand("deadline buy food /by 2023 October 10 "));
        assertEquals(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT, exception.getMessage());

        // Test Case 3: Missing Task Description
        exception = assertThrows(DukeException.class, () -> new DeadlineCommand("deadline  /by 2023-10-09 "));
        assertEquals(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT, exception.getMessage());

        // Test Case 4: Missing Date
        exception = assertThrows(DukeException.class, () -> new DeadlineCommand("deadline buy food /by "));
        assertEquals(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT, exception.getMessage());

        // Test Case 5: /by typo
        exception = assertThrows(DukeException.class, () -> new DeadlineCommand("deadline buy food /byyyy 2023-10-05 "));
        assertEquals(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT, exception.getMessage());
    
    }
}
