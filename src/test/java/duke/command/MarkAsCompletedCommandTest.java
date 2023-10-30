package duke.command;

import java.util.List;
import java.util.ArrayList;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkAsCompletedCommandTest {
  private List<Task> taskList;
  private MessageDisplay display;
  @BeforeEach
  public void setUp() {
    taskList = new ArrayList<>(); // Reset the taskList before each test
    display = new MessageDisplay();
  }
  @org.junit.jupiter.api.AfterEach
  public void tearDown() {
    taskList.clear(); // Clear the taskList after each test
  }

  @Test
  public void testExecute() {
    AddTodoCommand add1 = new AddTodoCommand("Test Event 1");
    AddTodoCommand add2 = new AddTodoCommand("Test Event 2");
    MarkAsCompletedCommand command = new MarkAsCompletedCommand(0);
    add1.execute(display,taskList);
    add2.execute(display,taskList);
    command.execute(display,taskList);
    assertTrue(taskList.get(0).isCompleted());
    assertFalse(taskList.get(1).isCompleted());
  }
}
