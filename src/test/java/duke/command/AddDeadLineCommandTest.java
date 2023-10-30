package duke.command;

<<<<<<< Updated upstream
import duke.Duke;
import org.junit.jupiter.api.AfterEach;
=======
import duke.task.Task;
import java.util.List;
import duke.Duke;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
>>>>>>> Stashed changes
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeadLineCommandTest {

  Duke duke;
<<<<<<< Updated upstream
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
=======
  @BeforeEach
  public void setUp() {
    duke = new duke.Duke();
  }
  @org.junit.jupiter.api.AfterEach
  public void tearDown() {
    duke.taskList.clear(); // Clear the taskList after each test
>>>>>>> Stashed changes
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
    String taskName = "Test Deadline";
    LocalDateTime taskDueDate = LocalDateTime.now();
    AddDeadLineCommand command = new AddDeadLineCommand(taskName, taskDueDate);
    command.execute(duke.,duke.taskList);
    assertEquals(taskName, taskList.get(0).getTaskName());
>>>>>>> Stashed changes
  }

}
