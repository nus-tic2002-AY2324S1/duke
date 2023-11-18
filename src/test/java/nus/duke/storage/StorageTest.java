package nus.duke.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class StorageTest {
    private File tempFile;

    private static Stream<Arguments> getTasksAndContent() {
        Stream<Arguments> args = getTasksAndContentOfDeadline();
        args = Stream.concat(args, getTasksAndContentOfEvent());
        args = Stream.concat(args, getTasksAndContentOfTodo());
        return args;
    }

    private static Stream<Arguments> getTasksAndContentOfDeadline() {
        LocalDateTime t1909011830 = LocalDateTime.of(2019, 9, 1, 18, 30);
        LocalDateTime t1909010930 = LocalDateTime.of(2019, 9, 1, 9, 30);

        return Stream.of(
            Arguments.of(
                List.of("D | 0 | return book | 2019-09-01T18:30:00 | "),
                List.of(new Deadline("return book", t1909011830))
            ),
            Arguments.of(
                List.of("D | 0 | return book | 2019-09-01T18:30:00 | 1"),
                List.of(new Deadline("return book", t1909011830, false, new TaskAfterOption(1)))
            ),
            Arguments.of(
                List.of("D | 0 | return book | 2019-09-01T18:30:00 | 2019-09-01T09:30:00"),
                List.of(new Deadline("return book", t1909011830, false, new TaskAfterOption(t1909010930)))
            )
        );
    }

    private static Stream<Arguments> getTasksAndContentOfEvent() {
        LocalDateTime t1909011430 = LocalDateTime.of(2019, 9, 1, 14, 30);
        LocalDateTime t1909011630 = LocalDateTime.of(2019, 9, 1, 16, 30);
        LocalDateTime t1910151630 = LocalDateTime.of(2019, 10, 15, 16, 30);
        LocalDateTime t1909011530 = LocalDateTime.of(2019, 9, 1, 15, 30);

        return Stream.of(
            Arguments.of(
                List.of(
                    "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | ",
                    "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | "
                ),
                List.of(
                    new Event("project meeting", t1909011430, t1909011630),
                    new Event("project meeting", t1909011430, t1910151630)
                )
            ),
            Arguments.of(
                List.of("E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 5"),
                List.of(new Event("project meeting", t1909011430, t1910151630, false, new TaskAfterOption(5)))
            ),
            Arguments.of(
                List.of("E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-10-15T16:30:00 | 2019-09-01T15:30:00"),
                List.of(new Event("project meeting", t1909011430, t1910151630, false, new TaskAfterOption(t1909011530)))
            )
        );
    }

    private static Stream<Arguments> getTasksAndContentOfTodo() {
        LocalDateTime t1909010930 = LocalDateTime.of(2019, 9, 1, 9, 30);

        return Stream.of(
            Arguments.of(
                List.of("T | 1 | join sports club | "),
                List.of(new Todo("join sports club", true))
            ),
            Arguments.of(
                List.of("T | 1 | join sports club | 8"),
                List.of(new Todo("join sports club", true, new TaskAfterOption(8)))
            ),
            Arguments.of(
                List.of("T | 1 | join sports club | 2019-09-01T09:30:00"),
                List.of(new Todo("join sports club", true, new TaskAfterOption(t1909010930)))
            )
        );
    }

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

    @ParameterizedTest
    @ValueSource(strings = {
        "D | 0 | return book |",
        "D | 0 | return book | 2019-19-01T18:30:00 |",
        "D | 0 | return book | 2019-09-01T18:30:00 | abc",
        "D | 0 | return book | 2019-09-01T18:30:00 | 2019-19-01T18:30:00",
        "E | 0 | project meeting |",
        "E | 0 | project meeting | 2019-19-01T14:30:00 -> 2019-09-01T16:30:00 |",
        "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-19-01T16:30:00 |",
        "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | abc",
        "E | 0 | project meeting | 2019-09-01T14:30:00 -> 2019-09-01T16:30:00 | 2019-19-01T18:30:00",
        "T | read book |",
        "T | read book | abc",
        "T | read book | 2019-19-01T18:30:00"
    })
    void load_invalidContent(String content) throws IOException {
        Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        assertThrows(StorageOperationException.class, () -> new Storage(tempFile.getAbsolutePath()).load());
    }

    @ParameterizedTest
    @MethodSource("getTasksAndContent")
    void load_validContent(List<String> lines, List<AbstractTask> tasks)
        throws IOException, StorageOperationException {

        Files.write(tempFile.toPath(), lines, StandardCharsets.UTF_8);
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = storage.load();
        assertArrayEquals(tasks.toArray(), taskList.getAllTasks().toArray());
    }

    @ParameterizedTest
    @MethodSource("getTasksAndContent")
    void save_tasks(List<String> expectedLines, List<AbstractTask> tasks)
        throws StorageOperationException, IOException {
        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.save(new TaskList(tasks));

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);
        assertIterableEquals(expectedLines, lines);
    }
}
