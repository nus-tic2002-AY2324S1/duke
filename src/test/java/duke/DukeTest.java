package duke;

import duke.command.TaskType;
import duke.error.ErrorType;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.task.Task;
import duke.task.Events;
import duke.task.Deadlines;
import duke.task.ToDos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class DukeTest {
    String[] desc = {
            "Test Case 1",
            "Test Case 2 /by 2023-01-01"
    };

    @Test
    void testTodo() {
        String input = "todo " + desc[0] + "\nbye";

        // Assert
        String expectedOutput = "Got it. I've added this task:";
        expectedOutput += "\n";
        expectedOutput += "[T][ ] " + desc[0];
        expectedOutput += "\n";
        expectedOutput = "Now you have 1 task(s) in the list"; // Need to clean data/duke.txt before test

        runAndAssertTask(input, expectedOutput);
    }

    @Test 
    void testDeadline(){
        String input = "todo " + desc[1] + "\nbye";

        // Assert
        String expectedOutput = "Got it. I've added this task:";
        expectedOutput += "\n";
        expectedOutput += "[D][ ] " + desc[1] + " (by: Jan 1 2023)";
        expectedOutput += "\n";
        expectedOutput = "Now you have 2 task(s) in the list"; // Need to clean data/duke.txt before test

        runAndAssertTask(input, expectedOutput);
    }

    private void runAndAssertTask(String input, String expectedOutput) {
        // Save the original System.in and System.out
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        // Redirect System.out to capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Create a new ByteArrayInputStream for the input
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in); // Redirect System.in to provide input

            // Act
            Duke duke = new Duke();
            duke.run();
        } finally {
            // Reset System.in and System.out to restore normal behavior
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        // Assert
        assertTrue(outContent.toString().contains(expectedOutput.trim()));
    }
}
