package duke.command;

import duke.Duke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
<<<<<<< Updated upstream

public class DeleteCommandTest {

  Duke duke;
  AddTodoCommand add1;
  AddTodoCommand add2;
  DeleteCommand command;
  int taskIndex;

=======
import java.util.List;
import java.util.ArrayList;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
public class DeleteCommandTest {

  private List<Task> taskList;
  private MessageDisplay display;
>>>>>>> Stashed changes
  @BeforeEach
  public void setUp() {

    duke = new duke.Duke();
    taskIndex = 1;
    add1 = new AddTodoCommand("Test Event 1");
    add2 = new AddTodoCommand("Test Event 2");
    command = new DeleteCommand(taskIndex);
  }
<<<<<<< Updated upstream

  @org.junit.jupiter.api.AfterEach
  public void tearDown() {

    duke = null;
=======
  @AfterEach
  public void tearDown() {
    taskList.clear(); // Clear the taskList after each test
>>>>>>> Stashed changes
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
