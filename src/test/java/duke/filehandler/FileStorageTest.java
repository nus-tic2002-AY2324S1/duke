package duke.filehandler;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import duke.task.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class FileStorageTest {

  @Test
  public void testStore() throws IOException {
    // Specify the file path for testing
    String testFilePath = "./data/duke.txt";

    FileStorage fileStorage = new FileStorage();

    List<Task> taskList = new ArrayList<>();
    taskList.add(new TodoTask("Test Todo 1"));
    taskList.add(new DeadlineTask("Test Deadline 1", false, LocalDate.now().atStartOfDay()));
    taskList.add(new EventTask("Test Event 1", true, LocalDate.now().atStartOfDay(), LocalDate.now().atStartOfDay()));

    // Store the tasks in the file
    fileStorage.store(taskList);

    // Check if the file was created and contains the correct data
    File outputFile = new File(testFilePath);
    assertTrue(outputFile.exists());

    List<String> lines = java.nio.file.Files.readAllLines(outputFile.toPath());
    assertEquals(3, lines.size());

    assertEquals("T | 0 | Test Todo 1", lines.get(0));
    assertEquals("D | 0 | Test Deadline 1 | " + LocalDate.now().atStartOfDay().toString().replace("T"," ") , lines.get(1));
    assertEquals("E | 1 | Test Event 1 | " + LocalDate.now().atStartOfDay().toString().replace("T"," ") + " | " + LocalDate.now().atStartOfDay().toString().replace("T"," "), lines.get(2));
  }
}
