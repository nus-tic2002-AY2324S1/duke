package duke.command;

import duke.filehandler.FileStorage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the AddDeadlineCommand class.
 */
public class AddDeadlineCommandTest {

  private AddDeadlineCommand addDeadlineCommand;
  private List<Task> taskList;

  /**
   * Set up the test environment before each test case.
   */
  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    addDeadlineCommand = new AddDeadlineCommand("Test Deadline", LocalDateTime.now());
  }

  /**
   * Clear the test environment after each test case.
   */
  @AfterEach
  public void tearDown() {

    taskList.clear();
  }

  /**
   * Test the addition of a DeadlineTask to the task list.
   */
  @Test
  public void testAddsDeadlineTaskToTaskList() {

    addDeadlineCommand.execute(new FileStorage(), new MessageDisplay(), taskList);

    assertEquals(1, taskList.size());
    assertTrue(taskList.get(0) instanceof DeadlineTask);
    assertEquals("Test Deadline", taskList.get(0).getTaskName());
  }

}
