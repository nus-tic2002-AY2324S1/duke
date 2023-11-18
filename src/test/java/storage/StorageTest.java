import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import storage.Storage;
import exceptions.EmptyListException;
import exceptions.MissingTaskException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Priority;
import tasks.TaskList;
import tasks.ToDo;
import tasks.ToDoWithinPeriod;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageTest {

    @Test
    public void loadDataTest() throws IOException {
        // Test Set Up
        Storage s = new Storage("data/temp.txt");
        ArrayList<Task> tasks = s.loadData();

        // Creating new tasks to check against
        Deadline d = new Deadline("return book", LocalDate.parse("2023-10-18"));
        Task td = new ToDo("buy food");
        td.setTaskPriority(Priority.HIGH);
        Event e = new Event("project meeting", LocalDate.parse("2023-10-18"), LocalDate.parse("2023-10-20"));
        ToDoWithinPeriod tdp = new ToDoWithinPeriod("collect certificate", LocalDate.parse("2023-10-08"),
                LocalDate.parse("2023-10-20"));

        // test case 1: checks if all 3 tasks that were stored below are loaded properly
        assertEquals(4, tasks.size());
        assertEquals(d.toString(), tasks.get(0).toString());
        assertEquals(td.toString(), tasks.get(1).toString());
        assertEquals(e.toString(), tasks.get(2).toString());
        assertEquals(tdp.toString(), tasks.get(3).toString());
    }

    /**
     * Adds 3 Tasks of Each Task Type {@code Deadline}, {@code Event} and {@code ToDo} and checks if all
     * are written onto file.
     * 
     * @throws FileNotFoundException
     *
     */
    @Test
    public void storeDataTest() throws FileNotFoundException, EmptyListException, MissingTaskException {

        // test set up
        String tempFilePath = "data/tasks.txt";
        Storage storage = new Storage(tempFilePath);
        File tempFile = new File(tempFilePath);
        Scanner s = new Scanner(tempFile);
        TaskList tasks = new TaskList();

        // adding a few random tasks
        tasks.addDeadline("return book", LocalDate.parse("2023-10-18"));
        tasks.addToDo("buy food");
        tasks.setPriority(2, Priority.HIGH);
        tasks.addEvent("project meeting", LocalDate.parse("2023-10-18"), LocalDate.parse("2023-10-20"));
        tasks.addToDoWithinPeriod("collect certificate", LocalDate.parse("2023-10-08"),
                LocalDate.parse("2023-10-20"));

        // test case 1: checks if all 3 tasks have been added in the file
        storage.storeData(tasks);

        // assertion
        assertEquals("D|LOW|false|return book|2023-10-18", s.nextLine());
        assertEquals("T|HIGH|false|buy food", s.nextLine());
        assertEquals("E|LOW|false|project meeting|2023-10-18|2023-10-20", s.nextLine());
        assertEquals("T|LOW|false|collect certificate|2023-10-08|2023-10-20", s.nextLine());
    }
}
