package duke.command;

import duke.Duke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddEventCommandTest {

<<<<<<< Updated upstream
  Duke duke;
  String taskName;
  LocalDateTime taskFromDate;
  LocalDateTime taskToDate;
  AddEventCommand command;

=======
  private List<Task> taskList;
  private MessageDisplay display;
>>>>>>> Stashed changes
  @BeforeEach
  public void setUp() {

    duke = new duke.Duke();
    taskName = "Test Event";
    taskFromDate = LocalDateTime.now();
    taskToDate = LocalDateTime.now();
    command = new AddEventCommand(taskName, taskFromDate, taskToDate);
  }

  @AfterEach
  public void tearDown() {

    duke = null;
    taskName = null;
    taskFromDate = null;
    taskToDate = null;
    command = null;

  }
  @org.junit.jupiter.api.AfterEach
  public void tearDown() {
    taskList.clear(); // Clear the taskList after each test
  }

  @Test
  public void testConstructor() {

    assertEquals(taskName, command.taskName);
  }

  @Test
  public void testExecute() {
<<<<<<< Updated upstream

    command.execute(duke.userInterface.messageDisplay, duke.taskList);
    assertEquals(taskName, duke.taskList.get(0).getTaskName());
=======
    String taskName = "Test Event";
    LocalDateTime taskFromDate = LocalDateTime.now();
    LocalDateTime taskToDate = LocalDateTime.now();
    AddEventCommand command = new AddEventCommand(taskName, taskFromDate,taskToDate);
    command.execute(display,taskList);
    assertEquals(taskName, taskList.get(0).getTaskName());
>>>>>>> Stashed changes
  }

}
