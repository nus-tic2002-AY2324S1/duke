import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.DukeException;
import commands.add.AddToDo;

public class AddToDoTest {
    @Test
    public void addToDoConstructorTest() throws DukeException{
        // Test Set Up
        Throwable exception; 

        // Working Test Case
        AddToDo t = new AddToDo("todo eat dinner");

        // Test Case 1: Missing Task Description
        exception = assertThrows(DukeException.class, () -> new AddToDo("todo "));
        assertEquals("Oops, missing todo description!", exception.getMessage());
    }
}
