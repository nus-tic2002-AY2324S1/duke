package duke.command;

import duke.Duke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoCommandTest {

  Duke duke;
  String taskName;
  AddTodoCommand command;

  @BeforeEach
  public void setUp() {

    duke = new duke.Duke();
    taskName = "Test Event";
    command = new AddTodoCommand(taskName);
  }

  @AfterEach
  public void tearDown() {

    duke = null;
    command = null;
    taskName = null;
  }

  @Test
  public void testConstructor() {

    assertEquals(taskName, command.taskName);
  }

  @Test
  public void testExecute() {

    command.execute(duke.userInterface.messageDisplay, duke.taskList);
    assertEquals(taskName, duke.taskList.get(0).getTaskName());
  }

}
