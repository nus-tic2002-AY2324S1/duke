package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.filehandler.FileStorage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Tests the functionality of the OnCommand class for checking tasks on a specific date.
 */
public class OnCommandTest {

    private LocalDate checkDate;
    private List<Task> taskList;
    private ByteArrayOutputStream outputStream;
    private OnCommand onCommand;
    private MessageDisplay display;
    private FileStorage fileStorage;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {

        checkDate = LocalDate.now();
        taskList = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        onCommand = new OnCommand(checkDate);
        display = new MessageDisplay();
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
     * Test checking tasks with an empty task list for a specific date.
     */
    @Test
    void testCheckTasksWithEmptyTaskList() {

        System.setOut(new PrintStream(outputStream));
        onCommand.execute(fileStorage, display, taskList);
        System.setOut(System.out);
        String expectedOutput =
                "There's nothing on " + checkDate + System.lineSeparator() + MessageDisplay.LINE_BREAK
                        + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Test checking tasks with a non-empty task list for a specific date.
     */
    @Test
    void testCheckTasksWithNonEmptyTaskList() {

        LocalDate checkDate = LocalDate.now();
        System.setOut(new PrintStream(outputStream));
        Task task1 = new DeadlineTask("Task 1", checkDate.atStartOfDay());
        Task task2 = new DeadlineTask("Task 2", checkDate.atStartOfDay());
        taskList.add(task1);
        taskList.add(task2);

        onCommand.checkTasks(taskList, checkDate);
        System.setOut(System.out);
        String expectedOutput =
                "Here are the tasks in your list as of " + checkDate + System.lineSeparator()
                        + MessageDisplay.LINE_BREAK + System.lineSeparator()
                        + "1." + task1 + System.lineSeparator()
                        + "2." + task2 + System.lineSeparator()
                        + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Test checking tasks with a task list where tasks do not match the specified date.
     */
    @Test
    void testCheckTasksWithTaskNotMatchingDate() {

        LocalDate checkDate = LocalDate.now();
        LocalDate futureDate = checkDate.plusDays(1);
        System.setOut(new PrintStream(outputStream));
        Task task1 = new DeadlineTask("Task 1", checkDate.atStartOfDay());

        taskList.add(task1);

        onCommand.checkTasks(taskList, futureDate);
        System.setOut(System.out);
        String expectedOutput =
                "There's nothing on " + futureDate + System.lineSeparator() + MessageDisplay.LINE_BREAK
                        + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

}
