package task;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("test description", "2023/10/20 2359");
        Assertions.assertEquals("[D][҉҉҉] Test description (by: 20 Oct 2023, 11PM - Friday)", deadline.toString());
    }

    @Test
    public void toStorageString() {
        Deadline deadline = new Deadline("test description", "2023/10/20 2359");
        Assertions.assertEquals("[D][҉҉҉] Test description (by: 2023/10/20 2359)", deadline.toStorageString());
    }
}