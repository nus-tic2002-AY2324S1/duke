import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventTest {
    @Test
    public void toSaveTest() {
        Event event1 = new Event("Homework", "midnight", "noon");
        Event event2 = new Event("Final", "04/12/2024 9am", "11 am");

        event2.setDone();

        //The format should look as follows:
        assertEquals("E | 0 | Homework | midnight | noon", event1.toSave());
        assertEquals("E | 1 | Final | 04/12/2024 9am | 11 am", event2.toSave());

        event1.setDone();
        event2.setNotDone();

        //when you swap the marks, the binary signs should change
        assertEquals("E | 1 | Homework | midnight | noon", event1.toSave());
        assertEquals("E | 0 | Final | 04/12/2024 9am | 11 am", event2.toSave());
    }

    @Test
    public void toDisplayTest() {
        Event event1 = new Event("Homework", "midnight", "noon");
        Event event2 = new Event("Final", "04/12/2024 9am", "11 am");

        event1.setDone();

        //toDisplay() should behave similarly to toSaveString()
        assertEquals("[E][X] Homework (from: midnight to: noon)", event1.toDisplay());
        //with no spaces between the two sets of brackets
        assertNotEquals("[E] [X] Homework (from: midnight to: noon)", event1.toDisplay());

        event1.setNotDone();
        event2.setDone();

        //when you swap the marks, the "X" should become " " and vice versa
        assertEquals("[E][ ] Homework (from: midnight to: noon)", event1.toDisplay());
        assertEquals("[E][X] Final (from: 04/12/2024 9am to: 11 am)", event2.toDisplay());
    }
}