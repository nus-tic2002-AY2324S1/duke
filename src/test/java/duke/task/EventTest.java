package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetTaskType() {
        Event event = new Event("Test Event");
        assertEquals(TaskType.E, event.getTaskType());
    }

    @Test
    public void testGetEventStartAndEndAt() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plus(10, ChronoUnit.DAYS);
        Event event = new Event("Test Event", start, end);
        assertEquals(start, event.getEventStartAt());
        assertEquals(end, event.getEventEndAt());
    }

    @Test
    public void testSetEventStartAndEndAt() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plus(10, ChronoUnit.DAYS);
        Event event = new Event("Test Event");
        event.setEventStartAt(start);
        event.setEventEndAt(end);
        assertEquals(start, event.getEventStartAt());
        assertEquals(end, event.getEventEndAt());

    }

    @Test
    public void testStatusIconForUndoneEvent() {
        Event event = new Event("Test Event");
        assertEquals("[E][ ]", event.getStatusIcon());
    }

    @Test
    public void testStatusIconForDoneEvent() {
        Event event = new Event("Test Event");
        event.markAsDone();
        assertEquals("[E][X]", event.getStatusIcon());
    }
}
