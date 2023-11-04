package duke.filehandler;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the FileStorage class for saving tasks to a file.
 */
public class FileStorageTest {

  /**
   * Test storing a list of tasks in a file.
   *
   * @throws IOException if there is an issue with file operations.
   */
  @Test
  public void testStore() throws IOException {

    List<Task> taskList = new ArrayList<>();
    taskList.add(new TodoTask("Test Todo 1"));
    taskList.add(new DeadlineTask("Test Deadline 1", false, LocalDate.now().atStartOfDay()));
    taskList.add(new EventTask("Test Event 1", true, LocalDate.now().atStartOfDay(), LocalDate.now().atStartOfDay()));

    FileStorage fileStorage = new FileStorage();

    // Store the tasks in the file
    fileStorage.store(taskList);

    // Specify the file path for testing
    String testFilePath = "./data/duke.txt";

    // Check if the file was created and contains the correct data
    File outputFile = new File(testFilePath);
    assertTrue(outputFile.exists());

    List<String> lines = java.nio.file.Files.readAllLines(outputFile.toPath());
    assertEquals(3, lines.size());

    assertEquals("T | 0 | Test Todo 1", lines.get(0));
    assertEquals("D | 0 | Test Deadline 1 | " + LocalDate.now().atStartOfDay().toString().replace("T", " "), lines.get(1));
    assertEquals("E | 1 | Test Event 1 | " + LocalDate.now().atStartOfDay().toString().replace("T", " ") + " | " + LocalDate.now().atStartOfDay().toString().replace("T", " "), lines.get(2));
  }
}
