package duke.command;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

  private DeleteCommand deleteCommand;
  private List<Task> taskList;

  @BeforeEach
  public void setUp() {

    taskList = new ArrayList<>();
    deleteCommand = new DeleteCommand(1);
  }

  @AfterEach
  public void tearDown() {

    taskList.clear();
  }

  @Test
  public void testDeleteTask() {

    Task task1 = new TodoTask("Task 1");
    Task task2 = new TodoTask("Task 2");
    taskList.add(task1);
    taskList.add(task2);

    int initialSize = taskList.size();

    deleteCommand.execute(new FileStorage(), new MessageDisplay(), taskList);

    assertEquals(initialSize - 1, taskList.size());

    assertEquals(task1.getTaskName(), taskList.get(0).getTaskName());
  }

}
