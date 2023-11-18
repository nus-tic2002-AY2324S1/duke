package Duke;

import Duke.ExceptionClasses.DupeException;
import Duke.TaskClasses.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DupeTest {

    @Test
    public void testTaskToString() {
        // Create a task
        Task task = new Task("Sample Task");

        // Expected result
        String expected = "[ ] Sample Task";

        // Test
        assertEquals(expected, task.toString());
    }

    private Storage storage;

    @Test
    public void testStorageLoad() throws DupeException, IOException {
        // Create a temporary test file
        File testFile = createTestFile("T | 0 | Sample Task");

        // Initialize Storage with the test file path
        storage = new Storage(testFile.getPath());

        ArrayList<Task> loadedTasks = storage.load();

        String expectedTaskDescription = "Sample Task";


        assertEquals(1, loadedTasks.size());
        assertEquals(expectedTaskDescription, loadedTasks.get(0).getDescription());

        testFile.delete();
    }

    private File createTestFile(String content) throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }

        return tempFile;
    }

}
