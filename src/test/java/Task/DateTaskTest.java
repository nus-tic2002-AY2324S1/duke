package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTaskTest {
    @Test
    public void testConstructor() {
        DateTask dateTask = new DateTask("event", "Run JUnit test on dates", "2023-11-08");
        assertEquals("E", dateTask.getType());
        assertEquals("Run JUnit test on dates", dateTask.getDescription());
        assertEquals(LocalDate.parse("2023-11-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), dateTask.deadline);
    }
}