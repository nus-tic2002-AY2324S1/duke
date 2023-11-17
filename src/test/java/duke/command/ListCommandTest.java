package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Tests the functionality of the ListCommand class for listing tasks.
 */
public class ListCommandTest {

    private List<Task> taskList;
    private ByteArrayOutputStream outputStream;
    private ListCommand listCommand;

    private MessageDisplay messageDisplay;

    private FileStorage fileStorage;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {

        taskList = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        listCommand = new ListCommand();
        messageDisplay = new MessageDisplay();
        fileStorage = new FileStorage();
    }

    /**
     * Clear the task list after each test case.
     */
    @AfterEach
    public void tearDown() {

        taskList.clear();
    }

    /**
     * Test printing an empty task list.
     */
    @Test
    void testPrintListWithEmptyTaskList() {

        System.setOut(new PrintStream(outputStream));
        listCommand.execute(fileStorage, messageDisplay, taskList);
        String expectedOutput =
                "There's nothing in your list" + System.lineSeparator() + MessageDisplay.LINE_BREAK
                        + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out);
    }

    /**
     * Test printing a non-empty task list.
     */
    @Test
    void testPrintTaskList() {

        taskList.add(new TodoTask("Test Task 1"));
        taskList.add(new TodoTask("Test Task 2"));

        System.setOut(new PrintStream(outputStream));
        listCommand.printList(taskList);

        String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
                + MessageDisplay.LINE_BREAK + System.lineSeparator()
                + "1." + taskList.get(0).toString() + System.lineSeparator()
                + "2." + taskList.get(1).toString() + System.lineSeparator()
                + MessageDisplay.LINE_BREAK + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out);
    }

}
