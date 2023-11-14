import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import duke.commands.MarkCommand;
import duke.exceptions.DukeException;

public class MarkCommandTest {

    @Test
    public void markCommandConstructorTest(){
        Throwable exception; 

        // Test Case 1: Missing Item Number 
        exception = assertThrows(DukeException.class, () -> new MarkCommand("mark "));
        assertEquals("Missing item number!", exception.getMessage());

        // Test Case 2: Invalid Item Number 
        exception = assertThrows(DukeException.class, () -> new MarkCommand("mark d"));
        assertEquals("Ensure the item number is a valid integer!", exception.getMessage());
    }
}
