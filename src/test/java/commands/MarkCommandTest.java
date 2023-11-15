import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import commands.MarkCommand;
import constants.ErrorMessages;
import exceptions.DukeException;

public class MarkCommandTest {

    @Test
    public void markCommandConstructorTest(){
        Throwable exception; 

        // Test Case 1: Missing Item Number 
        exception = assertThrows(DukeException.class, () -> new MarkCommand("mark "));
        assertEquals(ErrorMessages.INVALID_MARK_COMMAND_FORMAT, exception.getMessage());

        // Test Case 2: Invalid Item Number 
        exception = assertThrows(DukeException.class, () -> new MarkCommand("mark d"));
        assertEquals(ErrorMessages.INVALID_MARK_COMMAND_FORMAT, exception.getMessage());
    }
}
