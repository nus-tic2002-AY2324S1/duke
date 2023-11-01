package nus.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    @Test
    void load_existingFile_validContent_deadline_basic() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "D | 0 | return book | 2019-09-01T18:30:00 | "
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Deadline deadline = (Deadline) taskList.getTask(0);
        assertFalse(deadline.getDone());
        assertEquals("return book", deadline.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), deadline.getBy());
        assertNull(deadline.getAfterOption());
    }

    @Test
    void load_existingFile_validContent_deadline_afterTask() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "D | 0 | return book | 2019-09-01T18:30:00 | 1"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Deadline deadline = (Deadline) taskList.getTask(0);
        assertFalse(deadline.getDone());
        assertEquals("return book", deadline.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), deadline.getBy());
        assertNotNull(deadline.getAfterOption());
        assertTrue(deadline.getAfterOption().isAfterTask());
        assertFalse(deadline.getAfterOption().isAfterTime());
        assertEquals(1, deadline.getAfterOption().getTaskNumber());
    }

    @Test
    void load_existingFile_validContent_deadline_afterTime() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "D | 0 | return book | 2019-09-01T18:30:00 | 2019-09-01T09:30:00"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Deadline deadline = (Deadline) taskList.getTask(0);
        assertFalse(deadline.getDone());
        assertEquals("return book", deadline.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 18, 30), deadline.getBy());
        assertNotNull(deadline.getAfterOption());
        assertFalse(deadline.getAfterOption().isAfterTask());
        assertTrue(deadline.getAfterOption().isAfterTime());
        assertEquals(LocalDateTime.of(2019, 9, 1, 9, 30), deadline.getAfterOption().getDateTime());
    }

    @Test
    void load_existingFile_validContent_event_basic() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | ",
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | "
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(2, taskList.getAllTasks().size());

        Event event1 = (Event) taskList.getTask(0);
        assertFalse(event1.getDone());
        assertEquals("project meeting", event1.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), event1.getFrom());
        assertEquals(LocalDateTime.of(2019, 9, 1, 16, 30), event1.getTo());
        assertNull(event1.getAfterOption());

        Event event2 = (Event) taskList.getTask(1);
        assertFalse(event2.getDone());
        assertEquals("project meeting", event2.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), event2.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), event2.getTo());
        assertNull(event2.getAfterOption());
    }

    @Test
    void load_existingFile_validContent_event_afterTask() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 5"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Event event = (Event) taskList.getTask(0);
        assertFalse(event.getDone());
        assertEquals("project meeting", event.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), event.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), event.getTo());
        assertNotNull(event.getAfterOption());
        assertTrue(event.getAfterOption().isAfterTask());
        assertFalse(event.getAfterOption().isAfterTime());
        assertEquals(5, event.getAfterOption().getTaskNumber());
    }

    @Test
    void load_existingFile_validContent_event_afterTime() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 2019-09-01T15:30:00"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Event task = (Event) taskList.getTask(0);
        assertFalse(task.getDone());
        assertEquals("project meeting", task.getDescription());
        assertEquals(LocalDateTime.of(2019, 9, 1, 14, 30), task.getFrom());
        assertEquals(LocalDateTime.of(2019, 10, 15, 16, 30), task.getTo());
        assertNotNull(task.getAfterOption());
        assertFalse(task.getAfterOption().isAfterTask());
        assertTrue(task.getAfterOption().isAfterTime());
        assertEquals(LocalDateTime.of(2019, 9, 1, 15, 30), task.getAfterOption().getDateTime());
    }

    @Test
    void load_existingFile_validContent_todo_basic() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "T | 1 | join sports club | "
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Todo todo = (Todo) taskList.getTask(0);
        assertTrue(todo.getDone());
        assertEquals("join sports club", todo.getDescription());
        assertNull(todo.getAfterOption());
    }

    @Test
    void load_existingFile_validContent_todo_afterTask() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "T | 1 | join sports club | 8"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Todo todo = (Todo) taskList.getTask(0);
        assertTrue(todo.getDone());
        assertEquals("join sports club", todo.getDescription());
        assertNotNull(todo.getAfterOption());
        assertTrue(todo.getAfterOption().isAfterTask());
        assertFalse(todo.getAfterOption().isAfterTime());
        assertEquals(8, todo.getAfterOption().getTaskNumber());
    }

    @Test
    void load_existingFile_validContent_todo_afterTime() throws IOException, StorageOperationException {
        Collection<String> lines = List.of(
            "T | 1 | join sports club | 2019-09-01T09:30:00"
        );
        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertEquals(1, taskList.getAllTasks().size());

        Todo todo = (Todo) taskList.getTask(0);
        assertTrue(todo.getDone());
        assertEquals("join sports club", todo.getDescription());
        assertNotNull(todo.getAfterOption());
        assertFalse(todo.getAfterOption().isAfterTask());
        assertTrue(todo.getAfterOption().isAfterTime());
        assertEquals(LocalDateTime.of(2019, 9, 1, 9, 30), todo.getAfterOption().getDateTime());
    }

    @Test
    void save_deadline_basic() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "D | 0 | return book | 2019-09-01T18:30:00 | "
        );

        AbstractTask deadline = new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830"));
        TaskList taskList = new TaskList();
        taskList.addTask(deadline);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_deadline_afterTask() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "D | 0 | return book | 2019-09-01T18:30:00 | 1"
        );

        AbstractTask deadline = new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830"));
        deadline.setAfterOption(new TaskAfterOption(1));
        TaskList taskList = new TaskList();
        taskList.addTask(deadline);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_deadline_afterTime() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "D | 0 | return book | 2019-09-01T18:30:00 | 2019-09-01T09:30:00"
        );

        AbstractTask deadline = new Deadline("return book", Parser.parseUserDateTime("2019-9-1 1830"));
        deadline.setAfterOption(new TaskAfterOption(Parser.parseUserDateTime("2019-9-1 0930")));
        TaskList taskList = new TaskList();
        taskList.addTask(deadline);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_event_basic() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = Arrays.asList(
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | ",
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | "
        );

        // Event 1
        AbstractTask event1 = new Event(
            "project meeting",
            Parser.parseUserDateTime("2019-9-1 1430"),
            Parser.parseUserDateTime("2019-9-1 1630"));

        // Event 2
        AbstractTask event2 = new Event(
            "project meeting",
            Parser.parseUserDateTime("2019-9-1 1430"),
            Parser.parseUserDateTime("2019-10-15 1630"));

        TaskList taskList = new TaskList();
        taskList.addTask(event1);
        taskList.addTask(event2);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_event_afterTask() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 5"
        );

        AbstractTask event = new Event(
            "project meeting",
            Parser.parseUserDateTime("2019-9-1 1430"),
            Parser.parseUserDateTime("2019-10-15 1630"));
        event.setAfterOption(new TaskAfterOption(5));
        TaskList taskList = new TaskList();
        taskList.addTask(event);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_event_afterTime() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 2019-09-01T15:30:00"
        );

        AbstractTask event = new Event(
            "project meeting",
            Parser.parseUserDateTime("2019-9-1 1430"),
            Parser.parseUserDateTime("2019-10-15 1630"));
        event.setAfterOption(new TaskAfterOption(Parser.parseUserDateTime("2019-9-1 1530")));
        TaskList taskList = new TaskList();
        taskList.addTask(event);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_todo_basic() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "T | 1 | join sports club | "
        );

        AbstractTask todo = new Todo("join sports club", true);
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_todo_afterTask() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "T | 1 | join sports club | 8"
        );

        AbstractTask todo = new Todo("join sports club", true);
        todo.setAfterOption(new TaskAfterOption(8));
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }

    @Test
    void save_todo() throws StorageOperationException, IOException {
        final Collection<String> expectedLines = List.of(
            "T | 1 | join sports club | 2019-09-01T09:30:00"
        );

        AbstractTask todo = new Todo("join sports club", true);
        todo.setAfterOption(new TaskAfterOption(Parser.parseUserDateTime("2019-9-1 0930")));
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(taskList);

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }
}
