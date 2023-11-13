import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.DukeException;
import commands.add.AddDeadline;

public class AddDeadlineTest {
    @Test
    public void addDeadlineConstructorTest() throws DukeException{
        // Test Set Up
        Throwable exception;
         
        // Working Test Case
        AddDeadline a = new AddDeadline("deadline buy food /by 2023-10-05");

        // Test Case 1: Missing /
        exception = assertThrows(DukeException.class, () -> new AddDeadline("deadline buy food by 2023-10-05 "));
        assertEquals("Please follow the correct format.", exception.getMessage());

        // Test Case 2: Incorrect Date Format
        exception = assertThrows(DukeException.class, () -> new AddDeadline("deadline buy food /by 2023 October 10 "));
        assertEquals("Please provide the date in this format: YYYY-MM-DD", exception.getMessage());

        // Test Case 3: Missing Task Description
        exception = assertThrows(DukeException.class, () -> new AddDeadline("deadline  /by 2023-10-09 "));
        assertEquals("Oops, missing task description!", exception.getMessage());

        // Test Case 4: Missing Date
        exception = assertThrows(DukeException.class, () -> new AddDeadline("deadline buy food /by "));
        assertEquals("Please provide the date in this format: YYYY-MM-DD", exception.getMessage());

        // Test Case 5: /by typo
        exception = assertThrows(DukeException.class, () -> new AddDeadline("deadline buy food /byyyy 2023-10-05 "));
        assertEquals("Ensure you have no trailing letters behind /by.", exception.getMessage());
    
    }
}
