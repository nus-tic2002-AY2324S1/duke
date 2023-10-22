package nus.duke.storage;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Todo;
import nus.duke.parser.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The `StorageTest` class contains JUnit tests for the `Storage` class, focusing on the loading and saving of tasks.
 */
class StorageTest {
    private File tempFile;

    /**
     * Sets up the test environment by creating a temporary storage file.
     *
     * @throws IOException if there is an I/O error.
     */
    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("storage", ".txt");
    }

    /**
     * Tears down the test environment by deleting the temporary storage file.
     */
    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
            tempFile = null;
        }
    }

    /**
     * Tests loading a file when the file does not exist. Expects an empty task list to be returned.
     *
     * @throws StorageOperationException if there is an issue with storage operations.
     */
    @Test
    void load_fileNotExists() throws StorageOperationException {
        String absolutePath = tempFile.getAbsolutePath();
        assertTrue(tempFile.delete());

        assertEquals(0, new Storage(absolutePath).load().size());
    }

    /**
     * Tests loading an existing file with an invalid deadline task format. Expects a StorageOperationException to be
     * thrown.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    void load_existingFile_invalidDeadline() throws IOException {
        String content = "D | 0 | return book";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "D | 0 | return book | 2019-19-01T18:30:00";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    /**
     * Tests loading an existing file with an invalid event task format. Expects a StorageOperationException to be
     * thrown.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    void load_existingFile_invalidEvent() throws IOException {
        String content = "E | 0 | project meeting";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "E | 0 | project meeting | 2019-19-01T14:30:00 -> 2019-09-01T16:30:00";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-19-01T16:30:00";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    /**
     * Tests loading an existing file with an invalid todo task format. Expects a StorageOperationException to be
     * thrown.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    void load_existingFile_invalidTodo() throws IOException {
        String content = "T | read book";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    /**
     * Tests loading an existing file with valid task content. Expects the loaded task list to match the expected tasks.
     *
     * @throws IOException               if there is an I/O error.
     * @throws StorageOperationException if there is an issue with storage operations.
     */
    @Test
    void load_existingFile_validContent() throws IOException, StorageOperationException {
        Collection<String> lines = Arrays.asList(
                "D | 0 | return book | 2019-09-01T18:30:00",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00",
                "T | 1 | join sports club"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(4, taskList.getAllTasks().size());

        // Task 1
        Deadline task1 = (Deadline) taskList.getTask(0);
        assertFalse(task1.getDone());
        assertEquals("return book", task1.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), task1.getBy());

        // Task 2
        Event task2 = (Event) taskList.getTask(1);
        assertFalse(task2.getDone());
        assertEquals("project meeting", task2.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task2.getFrom());
        assertEquals(LocalDateTime.of(2019, 9, 1, 16, 30), task2.getTo());

        // Task 3
        Event task3 = (Event) taskList.getTask(2);
        assertFalse(task3.getDone());
        assertEquals("project meeting", task3.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task3.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), task3.getTo());

        // Task 4
        Todo task4 = (Todo) taskList.getTask(3);
        assertTrue(task4.getDone());
        assertEquals("join sports club", task4.getDescription());
    }

    /**
     * Tests saving tasks to a file. Expects the saved content to match the expected lines.
     *
     * @throws StorageOperationException if there is an issue with storage operations.
     * @throws IOException               if there is an I/O error.
     */
    @Test
    void save() throws StorageOperationException, IOException {
        Collection<String> expectedLines = Arrays.asList(
                "D | 0 | return book | 2019-09-01T18:30:00",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00",
                "T | 1 | join sports club"
        );

        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830")));
        taskList.addTask(new Event(
                "project meeting",
                Parser.parseUserDateTime("2019-9-1 1430"),
                Parser.parseUserDateTime("2019-9-1 1630")));
        taskList.addTask(new Event(
                "project meeting",
                Parser.parseUserDateTime("2019-9-1 1430"),
                Parser.parseUserDateTime("2019-10-15 1630")));
        taskList.addTask(new Todo("join sports club", true));

        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }
}
