package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
//        Event event = new Event("test description", "2023/10/20 2359");
//        assertEquals("[E][҉҉҉] Test description (from: 20 Oct 2023, 11PM - Friday)", event.toString());
    }

    @Test
    void toStorageString() {
        Event event = new Event("test description", "2023/10/20 2359");
        assertEquals("[E][҉҉҉] Test description (from: 2023/10/20 2359)", event.toStorageString());
    }
}