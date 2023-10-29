package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import duke.task.Task;
import org.junit.jupiter.api.BeforeEach;
import duke.userinterface.UserInterface.MessageDisplay;
public class AddTodoCommandTest {

  List<Task> taskList;
  MessageDisplay display;

  @BeforeEach
  public void setUp() {
    new duke.Duke();
    taskList = new ArrayList<>(); // Reset the taskList before each test
    display = new MessageDisplay();
  }

  @Test
  public void testConstructor() {
    String taskName = "Test Event";
    AddTodoCommand command = new AddTodoCommand(taskName);
    assertEquals(taskName, command.taskName);

  }
  @Test
  public void testExecute() {
    String taskName = "Test Event";
    AddTodoCommand command = new AddTodoCommand(taskName);
    command.execute(display,taskList);
    assertEquals(taskName, taskList.get(0).getTaskName());
  }

}
