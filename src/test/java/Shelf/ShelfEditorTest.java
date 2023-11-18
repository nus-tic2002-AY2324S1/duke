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
        ShelfManager.getShelf().get(0).replaceDescription("Run 2nd test on edit function");
        ShelfEditor.editDescription(0);
        assertEquals("Run 2nd test on edit function", shelfManager.getShelf().get(0).getDescription());
    }

//    @Test
//    public void testEditDate() {
//        ShelfManager shelfManager = new ShelfManager();
//        shelfManager.addTask(new DateTask("Finish project", "deadline", "2023-11-08"));
//        ShelfEditor.editDate(0);
//        assertEquals(LocalDate.parse("2023-11-08", DateTimeFormatter.ISO_LOCAL_DATE), ((DateTask) shelfManager.getShelf().get(0)).getDeadline());
//    }
}