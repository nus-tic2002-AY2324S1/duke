package duke.util;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestUtil {
    @TempDir
    static Path testFolder;
    public static final String TEST_DATA_FOLDER = "src/test/java/data/storageFileTest";

    public static ArrayList<Task> getTestTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo(true, "return book"));
        LocalDateTime datetime = LocalDateTime.of(2023, 12, 2, 0, 0);
        tasks.add(new Deadline(false, "return book", datetime));
        LocalDateTime from = LocalDateTime.of(2023, 12, 2, 3, 30);
        LocalDateTime to = LocalDateTime.of(2023, 12, 2, 4, 0);
        tasks.add(new Event(true, "project meeting", from, to));
        return tasks;
    }

    public static Storage getStorageObject(String filename) {
        return new Storage(TEST_DATA_FOLDER + "/" + filename);
    }

}
