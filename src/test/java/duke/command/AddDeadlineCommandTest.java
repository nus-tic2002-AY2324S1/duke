package duke.command;

import duke.filehandler.FileStorage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddDeadlineCommandTest {

  private AddDeadlineCommand addDeadLineCommand;
  private List<Task> taskList;

  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    addDeadLineCommand = new AddDeadlineCommand("Test Deadline", LocalDateTime.now());
  }

  @AfterEach
  public void tearDown() {
    taskList.clear();
  }

  @Test
  public void TestAddsDeadlineTaskToTaskList() {

    addDeadLineCommand.execute(new FileStorage(), new MessageDisplay(), taskList);

    assertEquals(1, taskList.size());
    assertTrue(taskList.get(0) instanceof DeadlineTask);
    assertEquals("Test Deadline", taskList.get(0).getTaskName());
  }

}
