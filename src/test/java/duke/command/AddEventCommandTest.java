package duke.command;

import duke.filehandler.FileStorage;
import duke.task.EventTask;
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

public class AddEventCommandTest {

  private AddEventCommand addEventCommand;
  private List<Task> taskList;

  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    addEventCommand = new AddEventCommand("Test Event", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
  }

  @AfterEach
  public void tearDown() {
    taskList.clear();
  }

  @Test
  public void TestAddsEventTaskToTaskList() {

    addEventCommand.execute(new FileStorage(), new MessageDisplay(), taskList);
    assertEquals(1, taskList.size());
    assertTrue(taskList.get(0) instanceof EventTask);
    assertEquals("Test Event", taskList.get(0).getTaskName());
  }

}
