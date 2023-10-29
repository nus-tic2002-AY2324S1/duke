package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import org.junit.jupiter.api.BeforeEach;
public class DeleteCommandTest {

  List<Task> taskList;
  MessageDisplay display;
  @BeforeEach
  public void setUp() {
    new duke.Duke();
    taskList = new ArrayList<>(); // Reset the taskList before each test
    display = new MessageDisplay();
  }


  @Test
  public void testExecute() {
    AddTodoCommand add1 = new AddTodoCommand("Test Event 1");
    AddTodoCommand add2 = new AddTodoCommand("Test Event 2");
    int taskIndex = 1;
    DeleteCommand command = new DeleteCommand(taskIndex);
    add1.execute(display,taskList);
    add2.execute(display,taskList);
    assertEquals(taskList.size(),2);
    command.execute(display,taskList);
    assertEquals(taskList.size(),1);
  }
}
