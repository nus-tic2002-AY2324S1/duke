package duke.command;

import duke.Duke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

  Duke duke;
  AddTodoCommand add1;
  AddTodoCommand add2;
  DeleteCommand command;
  int taskIndex;

  @BeforeEach
  public void setUp() {

    duke = new duke.Duke();
    taskIndex = 1;
    add1 = new AddTodoCommand("Test Event 1");
    add2 = new AddTodoCommand("Test Event 2");
    command = new DeleteCommand(taskIndex);
  }

  @org.junit.jupiter.api.AfterEach
  public void tearDown() {

    duke = null;
  }

  @Test
  public void testExecute() {

    add1.execute(duke.userInterface.messageDisplay, duke.taskList);
    add2.execute(duke.userInterface.messageDisplay, duke.taskList);
    assertEquals(duke.taskList.size(), 2);
    command.execute(duke.userInterface.messageDisplay, duke.taskList);
    assertEquals(duke.taskList.size(), 1);
  }

}
