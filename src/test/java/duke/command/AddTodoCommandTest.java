package duke.command;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the AddTodoCommand class.
 */
public class AddTodoCommandTest {

  private AddTodoCommand addTodoCommand;
  private List<Task> taskList;

  /**
   * Set up the test environment before each test case.
   */
  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    addTodoCommand = new AddTodoCommand("Test Todo");
  }

  /**
   * Clear the test environment after each test case.
   */
  @AfterEach
  public void tearDown() {

    taskList.clear();
  }

  /**
   * Test the addition of a TodoTask to the task list.
   */
  @Test
  public void testAddsTodoTaskToTaskList() {

    addTodoCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    assertEquals(1, taskList.size());
    assertTrue(taskList.get(0) instanceof TodoTask);
    assertEquals("Test Todo", taskList.get(0).getTaskName());
  }

}
