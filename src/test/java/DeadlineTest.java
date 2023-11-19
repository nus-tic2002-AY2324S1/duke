import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineTest {
    @Test
    public void toSaveTest() {
        Deadline deadline1 = new Deadline("Homework", "midnight");
        Deadline deadline2 = new Deadline("Final", "05/24/2024");

        deadline1.setDone();

        //The format should look as follows:
        assertEquals("D | 1 | Homework | midnight", deadline1.toSave());

        deadline1.setDone();
        deadline2.setNotDone();

        //when you swap the marks, the binary signs should change
        assertEquals("D | 1 | Homework | midnight", deadline1.toSave());
        assertEquals("D | 0 | Final | 05/24/2024", deadline2.toSave());
    }

    @Test
    public void toDisplayTest() {
        Deadline deadline1 = new Deadline("Homework", "midnight");
        Deadline deadline2 = new Deadline("Final", "05/24/2024");

        deadline2.setDone();

        //toString() should behave similarly to storeString()
        assertEquals("[D][ ] Homework (by: midnight)", deadline1.toDisplay());
        //with no spaces between the two sets of brackets
        assertNotEquals("[D] [ ] Homework | midnight", deadline1.toDisplay());

        deadline1.setDone();
        deadline2.setNotDone();

        //when you swap the marks, the "X" should become " " and vice versa
        assertEquals("[D][X] Homework (by: midnight)", deadline1.toDisplay());
        assertEquals("[D][ ] Final (by: 05/24/2024)", deadline2.toDisplay());
    }
}