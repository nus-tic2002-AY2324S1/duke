package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Test Task");
        taskList.addTask(task);
        assertTrue(taskList.getTaskList().contains(task));
    }

    @Test
    public void testDeleteTask() throws IOException, DukeException {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Test Task");
        taskList.addTask(task);
        Task deletedTask = taskList.deleteTask(0);
        assertEquals(task, deletedTask);
    }

    @Test
    public void testDeleteTaskWithInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> {
            taskList.deleteTask(0);  // This should throw DukeException
        });
    }

    @Test
    public void testMarkTaskAsDone() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Test Task");
        taskList.addTask(task);
        Task markedTask = taskList.markTaskAsDone(0);
        assertTrue(markedTask.getStatus());
    }

    @Test
    public void testMarkTaskAsDoneWithInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> {
            taskList.markTaskAsDone(0);  // This should throw DukeException
        });
    }

    @Test
    public void testUnmarkTaskAsNotDone() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Test Task");
        task.markAsDone();
        taskList.addTask(task);
        Task unmarkedTask = taskList.unmarkTaskAsNotDone(0);
        assertFalse(unmarkedTask.getStatus());
    }

    @Test
    public void testUnmarkTaskAsNotDoneWithInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> {
            taskList.unmarkTaskAsNotDone(0);  // This should throw DukeException
        });

    }

}
