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

public class MarkAsCompletedCommandTest {

  private List<Task> taskList;
  private ByteArrayOutputStream outputStream;

  @BeforeEach
  void setUp() {

    taskList = new ArrayList<>();
    outputStream = new ByteArrayOutputStream();
  }

  @AfterEach
  public void tearDown() {

    taskList.clear();
  }

  @Test
  void testMarkingIncompleteTask() {

    Task task = new TodoTask("Test Task 1");
    taskList.add(task);

    System.setOut(new PrintStream(outputStream));
    MarkAsCompletedCommand markCommand = new MarkAsCompletedCommand(0);
    markCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    System.setOut(System.out);

    String expectedOutput = "That's some progress! I've marked this task as done:" + System.lineSeparator() +
        "   " + taskList.get(0).toString() + System.lineSeparator() + MessageDisplay.LINE_BREAK
        + System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void testMarkingCompletedTask() {

    Task task = new TodoTask("Test Task 1");
    task.markAsCompleted();
    taskList.add(task);

    System.setOut(new PrintStream(outputStream));
    MarkAsCompletedCommand markCommand = new MarkAsCompletedCommand(0);
    markCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    System.setOut(System.out);

    String expectedOutput = task.getTaskName() + " is already marked!" + System.lineSeparator() + MessageDisplay.LINE_BREAK + System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString());
  }

}
