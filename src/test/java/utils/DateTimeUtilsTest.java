package utils;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void parseNextDay() {
        assertTrue(Objects.requireNonNull(DateTimeUtils.parseNextDay("end-of-year"))
                          .toString().startsWith("2023-12-31T23:59"));
    }

}