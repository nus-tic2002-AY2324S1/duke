package duke.storage;

import duke.exception.FileStorageException;
import duke.task.Task;

import duke.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    static Path testFolder;

    @Test
    public void save_validTask_success() throws Exception {
        ArrayList<Task> tasks = TestUtil.getTestTasks();
        Storage storage = getTempStorage();
        storage.save(tasks);

        assertStorageFileEqual(TestUtil.getStorageObject("validData.txt"), storage);
    }

    @Test
    public void constructor_nullFilePath_exceptionThrow() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidData1.txt", "InvalidData2.txt", "InvalidData3.txt", "InvalidData4.txt",
            "InvalidData5.txt"})
    public void load_invalidFormat_exceptionThrown(String fileName) throws Exception {
        Storage storage = TestUtil.getStorageObject(fileName);
        assertThrows(FileStorageException.class, () -> storage.load());
    }

    @Test
    public void load_validFormat_success() throws Exception {
        ArrayList<Task> actualTasks = TestUtil.getStorageObject("validData.txt").load();
        ArrayList<Task> expectedTasks = TestUtil.getTestTasks();
        assertEquals(actualTasks.toString(), expectedTasks.toString());
    }

    @Test
    public void load_nonExistantFile_exceptionThrown() throws Exception {
        Storage storage = getTempStorage();
        assertThrows(FileStorageException.class, () -> storage.load());
    }

    @Test
    public void save_nonTask_exceptionThrown() throws Exception {
        Storage storage = getTempStorage();
        assertThrows(NullPointerException.class, () -> storage.save(null));
    }

    private void assertStorageFileEqual(Storage st1, Storage st2) throws Exception {
        assertTextFileEqual(Paths.get(st1.getPath()), Paths.get(st2.getPath()));
    }

    public void assertTextFileEqual(Path path1, Path path2) throws IOException {
        List<String> file1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> file2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join("\n", file1), String.join("\n", file2));
    }

    public static Storage getTempStorage() throws Exception {
        return new Storage(testFolder.resolve("temp.txt").toString());
    }


}
