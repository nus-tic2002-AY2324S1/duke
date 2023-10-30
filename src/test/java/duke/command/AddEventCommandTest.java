package duke.command;

import duke.Duke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddEventCommandTest {

  Duke duke;
  String taskName;
  LocalDateTime taskFromDate;
  LocalDateTime taskToDate;
  AddEventCommand command;

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
