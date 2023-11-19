package Storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Tasks.Task;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class StorageTest {

    /**
     * Tests the checkFolderExistence method of the Storage class.
     * This test ensures that the tasklist folder is created if it doesn't exist.
     */
    @Test
    public void testCheckFolderExistence() {
        Storage storage = new Storage();
        storage.checkFolderExistence();

        // Access storageFileFolder
        String storageFileFolder = "./tasklist";
        Path directoryPath = Paths.get(storageFileFolder);
        assertTrue(Files.exists(directoryPath), "Tasklist folder should exist");
    }

    /**
     * Tests the saveTasksToFile and loadTasksFromFile methods of the Storage class.
     * This test checks if tasks can be saved to a file and loaded back successfully.
     */
    @Test
    public void testSaveAndLoadTasksToFile() {
        Storage storage = new Storage();
        ArrayList<Task> tasksToSave = new ArrayList<>(Arrays.asList(
                new Task("Task 1", false),
                new Task("Task 2", true),
                new Task("Task 3", false)
        ));

        // Save tasks to the actual storage file
        storage.saveTasksToFile(tasksToSave, null);

        // Load tasks from the actual storage file
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();

        // Check if the loaded tasks match the original tasks
        assertEquals(tasksToSave.size(), loadedTasks.size(), "Number of tasks should match");
        for (int i = 0; i < tasksToSave.size(); i++) {
            Task originalTask = tasksToSave.get(i);
            Task loadedTask = loadedTasks.get(i);
            assertEquals(originalTask.toFileString(), loadedTask.toFileString(), "Tasks should match");
        }
    }

    /**
     * Cleans up all the files that were written onto disk for the tests
     * tasklist.txt and tasklist folder
     */

    @AfterEach
    public void cleanup() {
        Path tasklistFolderPath = Paths.get("tasklist");
        try {
            Files.walk(tasklistFolderPath)
                    .sorted((path1, path2) -> -path1.compareTo(path2))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}