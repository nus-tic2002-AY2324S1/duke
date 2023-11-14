import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exceptions.DukeException;
import duke.commands.DueCommand;

public class DueCommandTest {
    @Test
    public void dueCommandConstructorTest(){
        Throwable exception; 
        // Test Case 1: Missing Date
        exception = assertThrows(DukeException.class, () -> new DueCommand("due "));
        assertEquals("Missing date!", exception.getMessage());

        // Test Case 2: Date in the wrong format
        exception = assertThrows(DukeException.class, () -> new DueCommand("due 10 Oct 2023"));
        assertEquals("Please follow the correct format.", exception.getMessage());

        // Test Case 2: Invalid Date
        exception = assertThrows(DukeException.class, () -> new DueCommand("due 2023-02-56")); // invalid day
        assertEquals("Please provide the date in this format: YYYY-MM-DD", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> new DueCommand("due 2023-21-06")); // invalid month
        assertEquals("Please provide the date in this format: YYYY-MM-DD", exception.getMessage());
    }
}
