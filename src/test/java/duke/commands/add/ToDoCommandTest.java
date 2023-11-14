import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.DukeException;
import duke.commands.add.ToDoCommand;

public class ToDoCommandTest {
    @Test
    public void toDoCommandConstuctorTest() throws DukeException{
        // Test Set Up
        Throwable exception; 

        // Working Test Case
        ToDoCommand t = new ToDoCommand("todo eat dinner");

        // Test Case 1: Missing Task Description
        exception = assertThrows(DukeException.class, () -> new ToDoCommand("todo "));
        assertEquals("Oops, missing todo description!", exception.getMessage());
    }
}
