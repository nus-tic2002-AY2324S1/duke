package duke.command;

import duke.task.Task;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import duke.userinterface.UserInterface.MessageDisplay;
public class AddDeadLineCommandTest {

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
    String taskName = "Test Deadline";
    LocalDateTime taskDueDate = LocalDateTime.now();
    AddDeadLineCommand command = new AddDeadLineCommand(taskName, taskDueDate);
    assertEquals(taskName, command.taskName);
  }

  @Test
  public void testExecute() {
    String taskName = "Test Deadline";
    LocalDateTime taskDueDate = LocalDateTime.now();
    AddDeadLineCommand command = new AddDeadLineCommand(taskName, taskDueDate);
    command.execute(display,taskList);
    assertEquals(taskName, taskList.get(0).getTaskName());
  }

}
