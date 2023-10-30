package duke.command;

import duke.Duke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeadLineCommandTest {

  Duke duke;
  String taskName;
  LocalDateTime taskDueDate;
  AddDeadLineCommand command;

  @BeforeEach
  public void setUp() {

    duke = new duke.Duke();
    taskName = "Test Deadline";
    taskDueDate = LocalDateTime.now();
    command = new AddDeadLineCommand(taskName, taskDueDate);
  }

  @AfterEach
  public void tearDown() {

    duke = null;
    taskName = null;
    taskDueDate = null;
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
