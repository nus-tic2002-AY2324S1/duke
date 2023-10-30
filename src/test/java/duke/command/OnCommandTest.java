package duke.command;

import duke.task.Task;
import duke.task.DeadlineTask;
import java.time.LocalDate;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnCommandTest {

  private List<Task> taskList;
  private ByteArrayOutputStream outputStream;

  private OnCommand onCommand;

  @BeforeEach
  void setUp() {
    taskList = new ArrayList<>();
    outputStream = new ByteArrayOutputStream();
    onCommand = new OnCommand();
  }

  @AfterEach
  public void tearDown() {
    taskList.clear();
  }

  @Test
  void testCheckTasksWithEmptyTaskList() {
    LocalDate checkDate = LocalDate.now();
    System.setOut(new PrintStream(outputStream));
    onCommand.checkTasks(new MessageDisplay(), taskList, checkDate);
    System.setOut(System.out);
    String expectedOutput = "There's nothing on " + checkDate + System.lineSeparator() + MessageDisplay.LINE_BREAK +System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void testCheckTasksWithNonEmptyTaskList() {
    LocalDate checkDate = LocalDate.now();
    System.setOut(new PrintStream(outputStream));
    Task task1 = new DeadlineTask("Task 1", checkDate.atStartOfDay());
    Task task2 = new DeadlineTask("Task 2", checkDate.atStartOfDay());
    taskList.add(task1);
    taskList.add(task2);

    onCommand.checkTasks(new MessageDisplay(), taskList, checkDate);
    System.setOut(System.out);
    String expectedOutput = "Here are the tasks in your list as of " + checkDate + System.lineSeparator() + MessageDisplay.LINE_BREAK + System.lineSeparator() +
        "1."+task1.toString() + System.lineSeparator() +
        "2."+task2.toString() + System.lineSeparator() +
        MessageDisplay.LINE_BREAK + System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void testCheckTasksWithTaskNotMatchingDate() {
    LocalDate checkDate = LocalDate.now();
    LocalDate futureDate = checkDate.plusDays(1);
    System.setOut(new PrintStream(outputStream));
    Task task1 = new DeadlineTask("Task 1", checkDate.atStartOfDay());

    taskList.add(task1);

    onCommand.checkTasks(new MessageDisplay(), taskList, futureDate);
    System.setOut(System.out);
    String expectedOutput = "There's nothing on " + futureDate + System.lineSeparator() + MessageDisplay.LINE_BREAK +System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }
}
