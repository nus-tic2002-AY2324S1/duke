package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;
import duke.utils.Utils;
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

    @BeforeEach
    public void setUp() {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    public void testLoadValidFile() throws DukeException {
        createTestDataFile(TEST_FILE_PATH);

        ArrayList<Task> loadedTaskList = Storage.load();

        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(Utils.newTodoTask("todo Task 1"));
        expectedTasks.add(Utils.newEventTask("event Task 2 /from 11/11/2023 /to 20/11/2023"));
        expectedTasks.add(Utils.newDeadlineTask("deadline Task 3 /by 30/11/2023 1800"));


        assertEquals(expectedTasks.get(0).getDescription(), loadedTaskList.get(0).getDescription());
        assertEquals(expectedTasks.get(1).getDescription(), loadedTaskList.get(1).getDescription());
        assertEquals(expectedTasks.get(2).getDescription(), loadedTaskList.get(2).getDescription());

        deleteTestDataFile(TEST_FILE_PATH);
    }

    @Test
    public void testSaveTasks() throws DukeException, IOException {
        taskList.addTask(Utils.newTodoTask("todo Task 1"));
        taskList.addTask(Utils.newEventTask("event Task 2 /from 11/11/2023 /to 20/11/2023"));
        taskList.addTask(Utils.newDeadlineTask("deadline Task 3 /by 30/11/2023 1800"));

        Storage.save(taskList);

        File savedFile = new File("data/dukeOut.txt");
        assertTrue(savedFile.exists());

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
