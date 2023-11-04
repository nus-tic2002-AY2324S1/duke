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

public class AddTodoCommandTest {

  private AddTodoCommand addTodoCommand;
  private List<Task> taskList;

  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    addTodoCommand = new AddTodoCommand("Test Todo");
  }

  @AfterEach
  public void tearDown() {

    taskList.clear();
  }

  @Test
  public void TestAddsTodoTaskToTaskList() {

    addTodoCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    assertEquals(1, taskList.size());
    assertTrue(taskList.get(0) instanceof TodoTask);
    assertEquals("Test Todo", taskList.get(0).getTaskName());

  }

}
