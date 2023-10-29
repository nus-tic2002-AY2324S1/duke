package duke.command;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;
import duke.task.Task;
import org.junit.jupiter.api.BeforeEach;
import duke.userinterface.UserInterface.MessageDisplay;
public class AddEventCommandTest {

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
    LocalDateTime taskFromDate = LocalDateTime.now();
    LocalDateTime taskToDate = LocalDateTime.now();
    AddEventCommand command = new AddEventCommand(taskName, taskFromDate,taskToDate);
    assertEquals(taskName, command.taskName);
  }
  @Test
  public void testExecute() {
    new duke.Duke();
    List<Task> taskList = new ArrayList<>();
    String taskName = "Test Event";
    LocalDateTime taskFromDate = LocalDateTime.now();
    LocalDateTime taskToDate = LocalDateTime.now();
    AddEventCommand command = new AddEventCommand(taskName, taskFromDate,taskToDate);
    command.execute(display,taskList);
    assertEquals(taskName, taskList.get(0).getTaskName());
  }

}
