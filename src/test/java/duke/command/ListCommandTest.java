package duke.command;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import duke.task.Task;
import java.util.List;
import java.util.ArrayList;
import duke.task.TodoTask;
import org.junit.jupiter.api.BeforeEach;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private List<Task> taskList;
  private MessageDisplay display;
  @BeforeEach
  public void setUp() {
    new duke.Duke();
    System.setOut(new PrintStream(outContent)); // Redirect System.out to outContent
    taskList = new ArrayList<>(); // Reset the taskList before each test
    display = new MessageDisplay();
  }

  @AfterEach
  public void tearDown() {
    taskList.clear(); // Clear the taskList after each test
    System.setOut(System.out); // Restore the standard output stream
  }

  @Test
  public void testExecuteWhenTaskListIsEmpty() {
    ListCommand listCommand = new ListCommand();
    listCommand.execute(display,taskList);
    String expectedOutput = "There's nothing in your list\n" + display.LINE_BREAK+"\n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void testExecuteWhenTaskListIsNotEmpty() {
    TodoTask task1 = new TodoTask("Task 1");
    TodoTask task2 = new TodoTask("Task 2");
    TodoTask task3 = new TodoTask("Task 3");
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);

    ListCommand listCommand = new ListCommand();
    listCommand.execute(display,taskList);

    String expectedOutput = "Here are the tasks in your list:\n" +
        "1.[T][ ] Task 1\n" +
        "2.[T][ ] Task 2\n" +
        "3.[T][ ] Task 3\n" +
        "****************************************\n";
    assertEquals(expectedOutput, outContent.toString());
  }
}
