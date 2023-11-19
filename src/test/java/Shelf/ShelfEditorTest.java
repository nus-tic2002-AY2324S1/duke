package Shelf;

import Task.DateTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShelfEditorTest {

    @Test
    public void testEditDescription() {
        ShelfManager shelfManager = new ShelfManager();
        DateTask dateTask = new DateTask("event", "Run JUnit test on dates", "2023-11-08");
        dateTask.replaceDescription("Run 2nd test on edit function");
        assertEquals("Run 2nd test on edit function", dateTask.getDescription());
    }

//    @Test
//    public void testEditDate() {
//        ShelfManager shelfManager = new ShelfManager();
//        shelfManager.addTask(new DateTask("Finish project", "deadline", "2023-11-08"));
//        ShelfEditor.editDate(0);
//        assertEquals(LocalDate.parse("2023-11-08", DateTimeFormatter.ISO_LOCAL_DATE), ((DateTask) shelfManager.getShelf().get(0)).getDeadline());
//    }
}