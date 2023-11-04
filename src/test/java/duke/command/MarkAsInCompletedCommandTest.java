package duke.command;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functionality of the MarkAsInCompletedCommand class for marking tasks as incomplete.
 */
public class MarkAsInCompletedCommandTest {

  private List<Task> taskList;
  private ByteArrayOutputStream outputStream;

  /**
   * Set up the test environment before each test case.
   */
  @BeforeEach
  void setUp() {
    taskList = new ArrayList<>();
    outputStream = new ByteArrayOutputStream();
  }

  /**
   * Clear the task list after each test case.
   */
  @AfterEach
  public void tearDown() {
    taskList.clear();
  }

  /**
   * Test marking a completed task as incomplete.
   */
  @Test
  void testExecuteMarkingIncompleteTask() {
    Task task = new TodoTask("Test Task 1");
    task.markAsCompleted();
    taskList.add(task);

    System.setOut(new PrintStream(outputStream));
    MarkAsInCompletedCommand markCommand = new MarkAsInCompletedCommand(0);
    markCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    System.setOut(System.out);

    String expectedOutput = "Okay, you can do it at a later time:" + System.lineSeparator()
        + "   " + taskList.get(0).toString() + System.lineSeparator()
        + MessageDisplay.LINE_BREAK + System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }

  /**
   * Test marking an already incomplete task as incomplete.
   */
  @Test
  void testExecuteMarkingAlreadyIncompleteTask() {
    Task task = new TodoTask("Test Task 1");
    taskList.add(task);

    System.setOut(new PrintStream(outputStream));
    MarkAsInCompletedCommand markCommand = new MarkAsInCompletedCommand(0);
    markCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    System.setOut(System.out);

    String expectedOutput = "You did not complete " + task.getTaskName() + " before!" + System.lineSeparator()
        + MessageDisplay.LINE_BREAK + System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }
}
