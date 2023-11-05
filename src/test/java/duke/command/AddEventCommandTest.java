package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.filehandler.FileStorage;
import duke.task.EventTask;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Tests the functionality of the AddEventCommand class.
 */
public class AddEventCommandTest {

    private AddEventCommand addEventCommand;
    private List<Task> taskList;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {

        taskList = new ArrayList<>();
        addEventCommand =
            new AddEventCommand("Test Event", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    }

    /**
     * Clear the test environment after each test case.
     */
    @AfterEach
    public void tearDown() {

        taskList.clear();
    }

    /**
     * Test the addition of an EventTask to the task list.
     */
    @Test
    public void testAddsEventTaskToTaskList() {

        addEventCommand.execute(new FileStorage(), new MessageDisplay(), taskList);

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof EventTask);
        assertEquals("Test Event", taskList.get(0).getTaskName());
    }

}
