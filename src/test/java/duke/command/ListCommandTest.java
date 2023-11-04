package duke.command;

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
 * Tests the functionality of the ListCommand class for listing tasks.
 */
public class ListCommandTest {

  private List<Task> taskList;
  private ByteArrayOutputStream outputStream;
  private ListCommand listCommand;

  /**
   * Set up the test environment before each test case.
   */
  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    outputStream = new ByteArrayOutputStream();
    listCommand = new ListCommand();
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
    listCommand.printList( taskList);
    String expectedOutput = "There's nothing in your list" + System.lineSeparator() + MessageDisplay.LINE_BREAK + System.lineSeparator();
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
    listCommand.printList( taskList);

    String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
        + MessageDisplay.LINE_BREAK + System.lineSeparator()
        + "1." + taskList.get(0).toString() + System.lineSeparator()
        + "2." + taskList.get(1).toString() + System.lineSeparator()
        + MessageDisplay.LINE_BREAK + System.lineSeparator();

    assertEquals(expectedOutput, outputStream.toString());
    System.setOut(System.out);
  }

}
