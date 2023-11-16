package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("test description", "2023/10/20 2359");
        Assertions.assertEquals("[D][҉҉҉] Test description (before: 20 Oct 2023, Fri - 11:59PM)", deadline.toString());
    }

    @Test
    public void toStorageString() {
        Deadline deadline = new Deadline("test description", "2023/10/20 2359");
        Assertions.assertEquals("D || 0 || test description || 2023/10/20 2359", deadline.toStorageString());
    }
}