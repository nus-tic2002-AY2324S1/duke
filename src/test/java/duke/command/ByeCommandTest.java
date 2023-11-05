package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ByeCommandTest {

    @Test
    public void testExecute() {
        ByeCommand byeCommand = new ByeCommand();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("testFilePath");

        // Redirect console output for testing
        ConsoleOutputRedirector.redirect();

        byeCommand.execute(taskList, ui, storage);
        String consoleOutput = ConsoleOutputRedirector.getOutput();

        assertTrue(consoleOutput.contains("Bye. Hope to see you again soon!"));

        // Reset console output
        ConsoleOutputRedirector.reset();
    }

    @Test
    public void testIsExit() {
        ByeCommand byeCommand = new ByeCommand();
        assertTrue(byeCommand.isExit());
    }
}


