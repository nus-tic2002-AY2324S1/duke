import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import commands.SetCommand;
import constants.ErrorMessages;
import exceptions.DukeException;

public class SetCommandTest {
    @Test
    public void setCommandConstructorTest(){
        Throwable exception; 

        // Test Case 1: Invalid Priority
        exception = assertThrows(DukeException.class, () -> new SetCommand("set 1 to lower"));
        assertEquals(ErrorMessages.INVALID_PRIORITY, exception.getMessage());

        // Test Case 2: Invalid Integer
        exception = assertThrows(DukeException.class, () -> new SetCommand("set d to high"));
        assertEquals(ErrorMessages.INVALID_COMMAND_FORMAT, exception.getMessage());
    }
}
