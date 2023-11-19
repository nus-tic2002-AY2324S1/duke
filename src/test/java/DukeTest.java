
import org.junit.jupiter.api.Test;
import util.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private Util helper = new Util();

    @Test
    public void testTransformDescription(){
        assertEquals("return book", helper.transformDescription("todo return book", "T"));
    }

    @Test
    public void testTransformDeadlineInformation(){
        assertEquals("14/05/2023 1800", helper.transformDeadlineInformation("deadline return book /by 14/05/2023 1800"));
    }
}
