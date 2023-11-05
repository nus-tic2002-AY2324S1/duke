package duke.storage;

import duke.Utils;
import duke.exception.DukeException;
import duke.task.*;
import duke.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private static final String TEST_FILE_PATH = "data/test_data.txt";
//    private static final String OUT_FILE_PATH =  "test_data_out.txt";

    @BeforeEach
    public void setUp() {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    public void testLoadValidFile() throws DukeException {
        // Create a test data file with tasks
        createTestDataFile(TEST_FILE_PATH);

        ArrayList<Task> loadedTaskList = storage.load();

        // Assert that the loaded tasks match the expected tasks
        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(Utils.newTodoTask("todo Task 1"));
        expectedTasks.add(Utils.newEventTask("event Task 2 /from 11/11/2023 /to 20/11/2023"));
        expectedTasks.add(Utils.newDeadlineTask("deadline Task 3 /by 30/11/2023 1800"));


        assertEquals(expectedTasks.get(0).getDescription(), loadedTaskList.get(0).getDescription());
        assertEquals(expectedTasks.get(1).getDescription(), loadedTaskList.get(1).getDescription());
        assertEquals(expectedTasks.get(2).getDescription(), loadedTaskList.get(2).getDescription());

        // Clean up: Delete the test data file
        deleteTestDataFile(TEST_FILE_PATH);
    }

    @Test
    public void testSaveTasks() throws DukeException, IOException {
        // Add some tasks to the task list
        taskList.addTask(Utils.newTodoTask("todo Task 1"));
        taskList.addTask(Utils.newEventTask("event Task 2 /from 11/11/2023 /to 20/11/2023"));
        taskList.addTask(Utils.newDeadlineTask("deadline Task 3 /by 30/11/2023 1800"));

        // Save the tasks to a test data file
        storage.save(taskList);

        // Check if the file exists
        File savedFile = new File("data/dukeOut.txt");
        assertTrue(savedFile.exists());

        // Clean up: Delete the test data file
        deleteTestDataFile("data/dukeOut.txt");
    }

    private void createTestDataFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("todo Task 1\n");
            fw.write("event Task 2 /from 11/11/2023 /to 20/11/2023\n");
            fw.write("deadline Task 3 /by 30/11/2023 1800\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestDataFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
