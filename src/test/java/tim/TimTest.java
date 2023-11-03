package tim;

import org.junit.jupiter.api.Test;
import tim.body.DateException;
import tim.tasks.Event;
import tim.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void timTestTodo() {
        ToDo todo = new ToDo ("test task name");
        assertEquals("test task name", todo.getDescription());
    }

    @Test
    public void timTestEvent() throws DateException {
        Event testEvent = new Event("test task name", "2020-10-10", "2021-10-11");
        assertEquals("test task name(from: Oct 10 2020 to: Oct 11 2021)", testEvent.getDescription());
        assertEquals('E', testEvent.getType());
    }
}