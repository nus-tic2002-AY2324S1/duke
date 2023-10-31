package nus.duke.storage;

import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
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
        String content = "D | 0 | return book |";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "D | 0 | return book | 2019-19-01T18:30:00 |";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "D | 0 | return book | 2019-09-01T18:30:00 | abc";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "D | 0 | return book | 2019-09-01T18:30:00 | 2019-19-01T18:30:00";
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
        String content = "E | 0 | project meeting |";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "E | 0 | project meeting | 2019-19-01T14:30:00 -> 2019-09-01T16:30:00 |";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-19-01T16:30:00 |";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | abc";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | 2019-19-01T18:30:00";
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
        String content = "T | read book |";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "T | read book | abc";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());

        content = "T | read book | 2019-19-01T18:30:00";
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
                "D | 0 | return book | 2019-09-01T18:30:00 | ",
                "D | 0 | return book | 2019-09-01T18:30:00 | 1",
                "D | 0 | return book | 2019-09-01T18:30:00 | 2019-09-01T09:30:00",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | ",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | ",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 5",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 2019-09-01T15:30:00",
                "T | 1 | join sports club | ",
                "T | 1 | join sports club | 8",
                "T | 1 | join sports club | 2019-09-01T09:30:00"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(10, taskList.getAllTasks().size());

        // Task 1
        Deadline task1 = (Deadline) taskList.getTask(0);
        assertFalse(task1.getDone());
        assertEquals("return book", task1.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), task1.getBy());
        assertNull(task1.getAfterOption());

        // Task 2
        Deadline task2 = (Deadline) taskList.getTask(1);
        assertFalse(task2.getDone());
        assertEquals("return book", task2.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), task2.getBy());
        assertNotNull(task2.getAfterOption());
        assertTrue(task2.getAfterOption().isAfterTask());
        assertFalse(task2.getAfterOption().isAfterTime());
        assertEquals(1, task2.getAfterOption().getTaskNumber());

        // Task 3
        Deadline task3 = (Deadline) taskList.getTask(2);
        assertFalse(task3.getDone());
        assertEquals("return book", task3.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), task3.getBy());
        assertNotNull(task3.getAfterOption());
        assertFalse(task3.getAfterOption().isAfterTask());
        assertTrue(task3.getAfterOption().isAfterTime());
        assertEquals(LocalDateTime.of(2019, 9, 1, 9, 30), task3.getAfterOption().getDateTime());

        // Task 4
        Event task4 = (Event) taskList.getTask(3);
        assertFalse(task4.getDone());
        assertEquals("project meeting", task4.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task4.getFrom());
        assertEquals(LocalDateTime.of(2019, 9, 1, 16, 30), task4.getTo());
        assertNull(task1.getAfterOption());

        // Task 5
        Event task5 = (Event) taskList.getTask(4);
        assertFalse(task5.getDone());
        assertEquals("project meeting", task5.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task5.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), task5.getTo());
        assertNull(task1.getAfterOption());

        // Task 6
        Event task6 = (Event) taskList.getTask(5);
        assertFalse(task6.getDone());
        assertEquals("project meeting", task6.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task6.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), task6.getTo());
        assertNotNull(task6.getAfterOption());
        assertTrue(task6.getAfterOption().isAfterTask());
        assertFalse(task6.getAfterOption().isAfterTime());
        assertEquals(5, task6.getAfterOption().getTaskNumber());

        // Task 7
        Event task7 = (Event) taskList.getTask(6);
        assertFalse(task7.getDone());
        assertEquals("project meeting", task7.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task7.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), task7.getTo());
        assertNotNull(task7.getAfterOption());
        assertFalse(task7.getAfterOption().isAfterTask());
        assertTrue(task7.getAfterOption().isAfterTime());
        assertEquals(LocalDateTime.of(2019, 9, 1, 15, 30), task7.getAfterOption().getDateTime());

        // Task 8
        Todo task8 = (Todo) taskList.getTask(7);
        assertTrue(task8.getDone());
        assertEquals("join sports club", task8.getDescription());
        assertNull(task1.getAfterOption());

        // Task 9
        Todo task9 = (Todo) taskList.getTask(8);
        assertTrue(task9.getDone());
        assertEquals("join sports club", task9.getDescription());
        assertNotNull(task9.getAfterOption());
        assertTrue(task9.getAfterOption().isAfterTask());
        assertFalse(task9.getAfterOption().isAfterTime());
        assertEquals(8, task9.getAfterOption().getTaskNumber());

        // Task 10
        Todo task10 = (Todo) taskList.getTask(9);
        assertTrue(task10.getDone());
        assertEquals("join sports club", task10.getDescription());
        assertNotNull(task10.getAfterOption());
        assertFalse(task10.getAfterOption().isAfterTask());
        assertTrue(task10.getAfterOption().isAfterTime());
        assertEquals(LocalDateTime.of(2019, 9, 1, 9, 30), task10.getAfterOption().getDateTime());
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
                "D | 0 | return book | 2019-09-01T18:30:00 | ",
                "D | 0 | return book | 2019-09-01T18:30:00 | 1",
                "D | 0 | return book | 2019-09-01T18:30:00 | 2019-09-01T09:30:00",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | ",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | ",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 5",
                "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 2019-09-01T15:30:00",
                "T | 1 | join sports club | ",
                "T | 1 | join sports club | 8",
                "T | 1 | join sports club | 2019-09-01T09:30:00"
        );

        TaskList taskList = new TaskList();

        // Task 1
        AbstractTask task1 = new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830"));
        taskList.addTask(task1);

        // Task 2
        AbstractTask task2 = new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830"));
        task2.setAfterOption(new TaskAfterOption(1));
        taskList.addTask(task2);

        // Task 3
        AbstractTask task3 = new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830"));
        task3.setAfterOption(new TaskAfterOption(Parser.parseUserDateTime("2019-9-1 0930")));
        taskList.addTask(task3);

        // Task 4
        AbstractTask task4 = new Event(
                "project meeting",
                Parser.parseUserDateTime("2019-9-1 1430"),
                Parser.parseUserDateTime("2019-9-1 1630"));
        taskList.addTask(task4);

        // Task 5
        AbstractTask task5 = new Event(
                "project meeting",
                Parser.parseUserDateTime("2019-9-1 1430"),
                Parser.parseUserDateTime("2019-10-15 1630"));
        taskList.addTask(task5);

        // Task 6
        AbstractTask task6 = new Event(
                "project meeting",
                Parser.parseUserDateTime("2019-9-1 1430"),
                Parser.parseUserDateTime("2019-10-15 1630"));
        task6.setAfterOption(new TaskAfterOption(5));
        taskList.addTask(task6);

        // Task 7
        AbstractTask task7 = new Event(
                "project meeting",
                Parser.parseUserDateTime("2019-9-1 1430"),
                Parser.parseUserDateTime("2019-10-15 1630"));
        task7.setAfterOption(new TaskAfterOption(Parser.parseUserDateTime("2019-9-1 1530")));
        taskList.addTask(task7);

        // Task 8
        AbstractTask task8 = new Todo("join sports club", true);
        taskList.addTask(task8);

        // Task 9
        AbstractTask task9 = new Todo("join sports club", true);
        task9.setAfterOption(new TaskAfterOption(8));
        taskList.addTask(task9);

        // Task 10
        AbstractTask task10 = new Todo("join sports club", true);
        task10.setAfterOption(new TaskAfterOption(Parser.parseUserDateTime("2019-9-1 0930")));
        taskList.addTask(task10);

        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }
}
