import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import duke.exceptions.DukeException;
import duke.commands.add.EventCommand;

public class EventCommandTest {
    @Test
    public void eventCommandConstructorTest() throws DukeException{
        // Test Set Up
        Throwable exception; 

        // Working Test Case
        EventCommand e = new EventCommand("event project meeting /from 2023-10-05 /to 2023-10-06");

        // Test Case 1: Missing /
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /from 2023-10-05 to 2023-10-06"));
        assertEquals("Please follow the correct format.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting from 2023-10-05 to 2023-10-06"));
        assertEquals("Please follow the correct format.", exception.getMessage());

        // Test Case 2: Incorrect Date Format
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /from 2O Oct 2023 /to 21 Oct 2023"));
        assertEquals("Please ensure both Start and End Date follows this format: YYYY-MM-DD", exception.getMessage());

        // Test Case 3: Missing Task Description
        exception = assertThrows(DukeException.class, () -> new EventCommand("event  /from 2023-10-05 /to 2023-10-06"));
        assertEquals("Oops, missing event description!", exception.getMessage());

        // Test Case 4: Missing Date
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /from /to 21 Oct 2023 "));
        assertEquals("Please ensure both Start and End Date follows this format: YYYY-MM-DD", exception.getMessage());

        // Test Case 5: Trailing letters after /from and /by
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /frommm 2023-10-05 /to 2023-10-06"));
        assertEquals("Ensure you have no trailing letters behind /from.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /from 2023-10-05 /tooo 2023-10-06"));
        assertEquals("Ensure you have no trailing letters behind /to.", exception.getMessage());

        // Test Case 6: End date should be later than Start Date
        exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /from 2023-10-08 /to 2023-10-05"));
        assertEquals("Your End Date should be later than your Start Date.", exception.getMessage());
    }
}