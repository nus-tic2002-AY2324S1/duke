package nus.duke.storage;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Task;
import nus.duke.data.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("storage", ".txt");
    }

    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
            tempFile = null;
        }
    }

    @Test
    void load_fileNotExists() throws StorageOperationException {
        String absolutePath = tempFile.getAbsolutePath();
        assertTrue(tempFile.delete());

        assertEquals(0, new Storage(absolutePath).load().size());
    }

    @Test
    void load_existingFile_invalidDeadline() throws IOException {
        String content = "D | 0 | return book";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    @Test
    void load_existingFile_invalidEvent() throws IOException {
        String content = "E | 0 | project meeting";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    @Test
    void load_existingFile_invalidTodo() throws IOException {
        String content = "T | read book";
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    @Test
    void load_existingFile_validContent() throws IOException, StorageOperationException {
        Collection<String> lines = Arrays.asList(
                "D | 0 | return book | June 6th",
                "E | 0 | project meeting | Aug 6th 2-4pm",
                "T | 1 | join sports club"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(3, taskList.getAllTasks().size());

        // Task 1
        Deadline task1 = (Deadline)taskList.getTask(0);
        assertFalse(task1.getDone());
        assertEquals("return book", task1.getDescription());
        assertEquals("June 6th", task1.getBy());

        // Task 2
        Event task2 = (Event)taskList.getTask(1);
        assertFalse(task2.getDone());
        assertEquals("project meeting", task2.getDescription());
        assertEquals("Aug 6th 2", task2.getFrom());
        assertEquals("4pm", task2.getTo());

        // Task 3
        Todo task3 = (Todo) taskList.getTask(2);
        assertTrue(task3.getDone());
        assertEquals("join sports club", task3.getDescription());
    }

    @Test
    void save() throws StorageOperationException, IOException {
        Collection<String> expectedLines = Arrays.asList(
                "D | 0 | return book | June 6th",
                "E | 0 | project meeting | Aug 6th 2-4pm",
                "T | 1 | join sports club"
        );

        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("return book", "June 6th"));
        taskList.addTask(new Event("project meeting", "Aug 6th 2", "4pm"));
        taskList.addTask(new Todo("join sports club", true));

        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }
}
