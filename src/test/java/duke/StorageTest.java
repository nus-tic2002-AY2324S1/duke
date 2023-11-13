import org.junit.jupiter.api.Test;

import duke.Storage;
import tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    /**
     * Adds 3 Tasks of Each Task Type {@code Deadline}, {@code Event} and {@code ToDo} and checks if all are written onto file. 
     * @throws FileNotFoundException
     *
     */
    @Test
    public void storeDataTest() throws FileNotFoundException{

        // test set up
        String tempFilePath = "data/tasks.txt";
        Storage storage = new Storage(tempFilePath);
        File tempFile = new File(tempFilePath);
        Scanner s = new Scanner(tempFile);
        TaskList tasks = new TaskList();

        // adding a few random tasks
        tasks.addDeadline("return book", LocalDate.parse("2023-10-18"));
        tasks.addToDo("buy food");
        tasks.addEvent("project meeting", LocalDate.parse("2023-10-18"), LocalDate.parse("2023-10-20"));

        // test case 1: checks if all 3 tasks have been added in the file
        storage.storeData(tasks);

        // assertion
        assertEquals(s.nextLine(),"D,false,return book,2023-10-18");
        assertEquals(s.nextLine(),"T,false,buy food");
        assertEquals(s.nextLine(),"E,false,project meeting,2023-10-18,2023-10-20");
    }
}
