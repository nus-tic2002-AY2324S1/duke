import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.DukeException;
import commands.add.ToDoCommand;
import constants.ErrorMessages;

public class ToDoCommandTest {
    @Test
    public void toDoCommandConstuctorTest() throws DukeException{
        // Test Set Up
        Throwable exception; 

        // Working Test Case
        ToDoCommand t = new ToDoCommand("todo eat dinner");

        // Test Case 1: Missing Task Description
        exception = assertThrows(DukeException.class, () -> new ToDoCommand("todo "));
        assertEquals(ErrorMessages.INVALID_TODO_FORMAT, exception.getMessage());
    }
}
